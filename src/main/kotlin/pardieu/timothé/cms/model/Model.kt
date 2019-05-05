package pardieu.timothé.cms.model

interface Model {
    fun getArticleList(): List<Article>

    fun getArticle(id: Int): Article?

    fun createArticle(title: String?, text:String?): String

    fun getComments(id: Int): List<Comment>?

    fun createComment(text: String, idArticle: String)
}