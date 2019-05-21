package pardieu.timothé.cms.model

interface Model {
    fun getArticleList(): List<Article>

    fun getArticle(id: Int): Article?

    fun createArticle(title: String?, text: String?): String

    fun getComments(id: Int): List<Comment>?

    fun getComment(id: Int): Comment?

    fun createComment(text: String, idArticle: String)

    fun register(username: String, password: String)

    fun getUserByUsername(username: String): User?

    fun removeArticle(id: Int): Unit

    fun removeComment(id: Int): Unit

}