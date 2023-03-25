
class Tree {
    var root : TreeNode? = null

    init {
        root = TreeNode(HtmlElement("", mutableMapOf()))
    }
    fun inOrderTraversal(){
        inOrderTraversalHelper(root)
    }
    private fun inOrderTraversalHelper(currentNode:TreeNode?){
        if(currentNode == null){
            return
        }

        for (i in currentNode.depth downTo 0) {
            print("\t\t")
        }
        print(currentNode.htmlElement.htmlTag)
        print(", ")
        print(currentNode.htmlElement.htmlAttributes)
        println()

        for (node in currentNode.children){

            inOrderTraversalHelper(node)

        }

    }
}

data class TreeNode(var htmlElement: HtmlElement){
    var parent : TreeNode? = null
        private set

    var children : MutableList<TreeNode?> = mutableListOf()
        private set
    var depth = 0
        private set
    var index = 0
        private set
    var childLowestIndex = 0
        private set

    fun addChild(htmlTag: String, nodeAttributes: MutableMap<String, String>) {
        val node = TreeNode(HtmlElement(htmlTag, nodeAttributes))
        node.parent = this
        node.index = this.childLowestIndex
        this.childLowestIndex++
        node.depth = this.depth + 1
        children.add(node)
    }

}