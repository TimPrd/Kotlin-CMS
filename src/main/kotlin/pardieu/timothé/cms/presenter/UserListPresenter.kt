package pardieu.timothé.cms.presenter

import pardieu.timothé.cms.model.Article
import pardieu.timothé.cms.model.User

interface UserListPresenter {

    fun start()

    interface View {
        fun displayUserList(list:List<User>)
    }

}