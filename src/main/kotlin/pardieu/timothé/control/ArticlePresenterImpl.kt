package pardieu.timothé.control

import pardieu.timothé.cms.presenter.ArticlePresenter
import pardieu.timothé.cms.model.Model

class ArticlePresenterImpl(val model: Model, val view: ArticlePresenter.View):
    ArticlePresenter {

    override fun start(id:Int) {
        val article = model.getArticle(id)
        if (article != null) {
            val comments = model.getComments(id)
            view.displayArticle(article, comments)
        }
        else {
            view.displayArticleNotFound()
        }
    }
}