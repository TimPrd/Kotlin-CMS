package pardieu.timothé.cms.presenter

import pardieu.timothé.cms.model.Article

interface ArticleListPresenter {

    fun start()

    interface View {
        fun displayArticleList(list:List<Article>)
    }

}