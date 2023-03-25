
class Tree {
    var root : TreeNode? = null

    init {
        root = TreeNode("", mutableMapOf())
    }
    fun inOrderTraversal(){
        inOrderTraversalHelper(root)
    }
    private fun inOrderTraversalHelper(currentNode:TreeNode?){
        if(currentNode == null){
            return
        }
        for (node in currentNode.children){
            if (node != null) {
                print(node.htmlTag)
            }
            inOrderTraversalHelper(node)

        }

    }
}

data class TreeNode(var htmlTag: String, var nodeAttributes: MutableMap<String,String> = mutableMapOf()){
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
        val node = TreeNode(htmlTag, nodeAttributes)
        node.parent = this
        node.index = this.childLowestIndex
        this.childLowestIndex++
        node.depth = this.depth + 1
        children.add(node)
    }
}