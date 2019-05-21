package pardieu.timothé.cms

import pardieu.timothé.cms.db.ConnectionPool
import pardieu.timothé.cms.db.MysqlModel
import pardieu.timothé.cms.model.Model
import pardieu.timothé.cms.presenter.ArticleListPresenter
import pardieu.timothé.cms.presenter.ArticlePresenter
import pardieu.timothé.cms.presenter.CommentPresenter
import pardieu.timothé.control.CommentPresenterImpl
import pardieu.timothé.control.ArticleListPresenterImpl
import pardieu.timothé.control.ArticlePresenterImpl

class AppComponent( mysqlUrl: String, mysqlUser: String,  mysqlPassword:String) {

    private val pool = ConnectionPool(mysqlUrl, mysqlUser, mysqlPassword)
    private val model = MysqlModel(getPool())

    fun getModel(): Model = model

    fun getPool(): ConnectionPool = pool


    fun getArticleListPresenter(view: ArticleListPresenter.View): ArticleListPresenter {
        return ArticleListPresenterImpl(getModel(), view )
    }

    fun getArticlePresenter(view: ArticlePresenter.View): ArticlePresenter {
        return ArticlePresenterImpl(getModel(), view )
    }

    fun getCommentPresenter(view: CommentPresenter.View): CommentPresenter {
        return CommentPresenterImpl(getModel(), view)
    }



}

