package pardieu.timothé.cms.presenter

import pardieu.timothé.cms.model.Article
import pardieu.timothé.cms.model.Comment

interface ArticlePresenter {
    fun start(id:Int, qParam:String?)

    interface View {
        fun displayArticle(article: Article?, comments: List<Comment>?)
        fun displayArticleNotFound()
        fun removeArticle(articleId: Int)
    }

}