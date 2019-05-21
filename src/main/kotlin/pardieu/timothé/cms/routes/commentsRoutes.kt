package pardieu.timothé.cms.routes

import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import kotlinx.coroutines.launch
import pardieu.timothé.cms.AppComponent
import pardieu.timothé.cms.model.Article
import pardieu.timothé.cms.presenter.ArticleListPresenter
import pardieu.timothé.cms.presenter.CommentPresenter
import pardieu.timothé.cms.tpl.IndexContext
import pardieu.timothé.cms.tpl.SampleSession

fun Route.commentsRoutes(appComponent: AppComponent) {

    get {
        val controller = appComponent.getArticleListPresenter(object : ArticleListPresenter.View {
            override fun displayArticleList(list: List<Article>) {
                val ctx = IndexContext(list, call.sessions.get<SampleSession>())
                launch {
                    call.respond(FreeMarkerContent("index.ftl", ctx, "e"))
                }
            }
        })
        controller.start()
    }

    post {
        val body = call.receiveParameters()
        //val controller = appComponent.(object : ArticleListPresenter.View {

        val id = appComponent.getModel().createComment(body["text"]!!, body["current"]!!)

        call.respondRedirect("/articles/${body["current"]!!}")

        /*val id = model.createArticle(, body["text"])
        if (id.equals("-1"))
            call.respondRedirect("/articles/")
        else
            call.respondRedirect("/articles/$id")*/
    }


    get("{id}") {
        val controller = appComponent.getCommentPresenter(object : CommentPresenter.View {
            override fun removeComment(commentId: Int) {
                launch {
                    appComponent.getModel().removeComment(commentId)
                    call.respondRedirect("/articles/")
                }
            }
        })
        val id = call.parameters["id"]!!.toIntOrNull()
        if (id == null)
            call.respond(HttpStatusCode.NotFound)
        else
            controller.removeComment(id, call.parameters["action"])
    }
}