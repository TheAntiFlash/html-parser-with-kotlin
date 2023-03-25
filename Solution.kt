import java.util.Stack

class Solution(html: String) {
    var htmlTree = Tree()
    private lateinit var state : ParserState
    //private var htmlElements = mutableListOf<HtmlElement>()
    init {
        parseToTree(html)
    }

   /* private fun parseToList(html : String){
        var startIndex : Int = 0
        var endIndex : Int
        for ((i,c) in html.withIndex()) {
            if(c == '<' && html[i+1] != '/'){
                state = ParserState.STATE_START_TAG
                startIndex = i
            }
            else if (state == ParserState.STATE_START_TAG){
                if(c == '>') {
                    state = ParserState.STATE_END_TAG
                    endIndex = i+1
                    htmlElements.add(HtmlElement(html.substring(startIndex, endIndex), getAttributesFromTag("")))
                }
            }

        }
    }*/
    private fun parseToTree(html : String){
        val stackOfChildrenForEachParent = Stack<Int>()
        stackOfChildrenForEachParent.push(0)

        var startIndex  = 0
        var endIndex: Int

        var currentTreeNode = htmlTree.root
        currentTreeNode?.htmlElement = HtmlElement("<html>", mutableMapOf())
        currentTreeNode?.addChild("", mutableMapOf())
        currentTreeNode = currentTreeNode!!.children[stackOfChildrenForEachParent.peek()]
        val tempVal = stackOfChildrenForEachParent.pop() + 1
        stackOfChildrenForEachParent.push(tempVal)
        stackOfChildrenForEachParent.push(0)

        for ((i,c) in html.withIndex()) {
            if(c == '<' && html[i+1] == '/' && stackOfChildrenForEachParent.size > 1){
                currentTreeNode = currentTreeNode?.parent
                stackOfChildrenForEachParent.pop()
            }
            else if(c == '<' && html[i+1] != '/'){
                state = ParserState.STATE_START_TAG
                startIndex = i

            }
            else if (state == ParserState.STATE_START_TAG){
                if( c == '>' && currentTreeNode?.htmlElement?.htmlTag != ""){
                    state = ParserState.STATE_END_TAG
                    endIndex = i+1
                    currentTreeNode?.addChild("", mutableMapOf())

                    currentTreeNode = currentTreeNode!!.children[stackOfChildrenForEachParent.peek()]
                    currentTreeNode?.htmlElement = HtmlElement(html.substring(startIndex, endIndex), getAttributesFromTag(html.substring(startIndex, endIndex)))
                    val temp = stackOfChildrenForEachParent.pop() + 1
                    stackOfChildrenForEachParent.push(temp)
                    stackOfChildrenForEachParent.push(0)

                }
                else if(c == '>') {
                    state = ParserState.STATE_END_TAG
                    endIndex = i+1
                    currentTreeNode?.htmlElement = HtmlElement(html.substring(startIndex, endIndex), getAttributesFromTag(html.substring(startIndex, endIndex)))

                }
            }
        }
    }

    private fun getAttributesFromTag(htmltag : String) : MutableMap<String,String>{
        val attributes = mutableMapOf<String,String>()
        val htmlTagSplit = htmltag.split(" ")
        for(i in htmlTagSplit)
        {
            val keyValue = i.split("=")
            if (keyValue.size >= 2) {
                attributes[keyValue[0]] = keyValue[1]
            }
        }

        return attributes
    }

}

