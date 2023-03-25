class Solution(html: String) {
    private var htmlTree = Tree()
    private lateinit var state : ParserState
    var htmlElements = mutableListOf<HtmlElement>()
    init {
        parseToTree(html)
    }

    private fun parseToTree(html : String){
        var startIndex : Int = 0
        var endIndex : Int = 0
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
    }

    fun getAttributesFromTag(html : String) : MutableMap<String,String>{
        return mutableMapOf()
    }

}

