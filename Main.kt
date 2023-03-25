
fun main (){
    val test = "<h1>Random Quote Generator</h1>\n" +
            "<div class=\"white-box\">\n" +
            "  <div class=\"quote\">\n" +
            "    <i class=\"fa fa-quote-left fa-3x\"></i> \n" +
            "    <p class=\"random-quote\" test=\"works\"> <span id=\"text\"></span></p>\n" +
            "  </div>\n" +
            "  <div class=\"random-author\">- <span id=\"author\"></span>\n" +
            "  </div>\n" +
            "  <div class=\"buttons\">" +
            "  </div>\n"


    val htmlparser = Solution(test)

    htmlparser.htmlTree.inOrderTraversal()
}