package pardieu.timothé.cms.presenter

import pardieu.timothé.cms.model.Article
import pardieu.timothé.cms.model.Comment


interface CommentPresenter {

    fun createComment()

    fun removeComment(id: Int, qParam: String?)

    interface View {
        fun removeComment(commentId: Int)
    }
}