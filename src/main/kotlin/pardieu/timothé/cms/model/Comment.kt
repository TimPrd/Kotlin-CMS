package pardieu.timothé.cms.model

data class Comment(
    val id: Int,
    val text: String,
    val idArticle: Int
)