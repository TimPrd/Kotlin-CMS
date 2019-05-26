package pardieu.timothé.cms.control

import pardieu.timothé.cms.presenter.ArticleListPresenter
import pardieu.timothé.cms.model.Model
import pardieu.timothé.cms.presenter.UserListPresenter

class UserListPresenterImpl(val model: Model, val view: UserListPresenter.View):
    UserListPresenter {

    override fun start() {
        val list = model.getUsers()
        view.displayUserList(list)
    }
}