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
import kotlinx.coroutines.launch
import pardieu.timothé.cms.AppComponent
import pardieu.timothé.cms.presenter.ArticleListPresenter
import pardieu.timothé.cms.presenter.ArticlePresenter
import pardieu.timothé.cms.model.Article
import pardieu.timothé.cms.model.Comment
import pardieu.timothé.cms.tpl.IndexContext

fun Route.commentsRoutes(appComponent: AppComponent) {

    get {
        val controller = appComponent.getArticleListPresenter(object : ArticleListPresenter.View {
            override fun displayArticleList(list: List<Article>) {
                val ctx = IndexContext(list)
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

        val id = appComponent.getModel().createComment( body["text"]!!, body["current"]!!)

        call.respondRedirect("/articles/${body["current"]!!}")

        /*val id = model.createArticle(, body["text"])
        if (id.equals("-1"))
            call.respondRedirect("/articles/")
        else
            call.respondRedirect("/articles/$id")*/
    }
}