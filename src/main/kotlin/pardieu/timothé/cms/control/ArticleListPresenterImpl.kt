package pardieu.timothé.cms.control

import pardieu.timothé.cms.presenter.ArticleListPresenter
import pardieu.timothé.cms.model.Model

class ArticleListPresenterImpl(val model: Model, val view: ArticleListPresenter.View):
    ArticleListPresenter {

    override fun start() {
        val list = model.getArticleList()
        view.displayArticleList(list)
    }
}