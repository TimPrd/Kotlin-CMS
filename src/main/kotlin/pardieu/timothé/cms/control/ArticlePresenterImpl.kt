package pardieu.timothé.cms.control

import pardieu.timothé.cms.model.Model
import pardieu.timothé.cms.presenter.ArticlePresenter

class ArticlePresenterImpl(val model: Model, val view: ArticlePresenter.View) :
    ArticlePresenter {

    override fun start(id: Int, qParam: String?) {
        val article = model.getArticle(id)
        when {
            article != null && qParam == null -> {
                val comments = model.getComments(id)
                view.displayArticle(article, comments)
            }
            article != null && qParam != null -> view.removeArticle(article.id)
            else -> view.displayArticleNotFound()
        }
    }
}