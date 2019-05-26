package pardieu.timothé.cms.control

import pardieu.timothé.cms.model.Model
import pardieu.timothé.cms.presenter.CommentPresenter

class CommentPresenterImpl(val model: Model, val view: CommentPresenter.View) :
    CommentPresenter {
    override fun createComment() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeComment(id: Int, qParam: String?) {
        val comment = model.getComment(id)
        if (comment != null) view.removeComment(comment.id)
    }
}
