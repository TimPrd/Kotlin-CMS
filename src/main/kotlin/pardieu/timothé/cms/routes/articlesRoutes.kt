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
import io.ktor.routing.route
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import kotlinx.coroutines.launch
import pardieu.timothé.cms.AppComponent
import pardieu.timothé.cms.model.Article
import pardieu.timothé.cms.model.Comment
import pardieu.timothé.cms.presenter.ArticleListPresenter
import pardieu.timothé.cms.presenter.ArticlePresenter
import pardieu.timothé.cms.tpl.IndexContext
import pardieu.timothé.cms.tpl.SampleSession

fun Route.articlesRoutes(appComponent: AppComponent) {

    get {
        val controller = appComponent.getArticleListPresenter(object : ArticleListPresenter.View {
            override fun displayArticleList(list: List<Article>) {
                //val u call.sessions.get<SampleSession>()
                // if session -> if isAdmin else
                val ctx = IndexContext(list, call.sessions.get<SampleSession>())
                println(ctx.session)
                launch {
                    call.respond(FreeMarkerContent("index.ftl", ctx, "e"))
                }
            }
        })
        controller.start()
    }
    get("{id}") {
        val controller = appComponent.getArticlePresenter(object : ArticlePresenter.View {

            override fun displayArticleNotFound() {
                launch {
                    call.respond(HttpStatusCode.NotFound)
                }
            }

            override fun displayArticle(
                article: Article?,
                comments: List<Comment>?
            ) {
                launch {
                    val currentSession = call.sessions.get<SampleSession>()
                    val isAdmin = (currentSession != null && currentSession.isAdmin )
                    call.respond(
                        FreeMarkerContent(
                            "article.ftl",
                            mapOf(
                                "article" to article,
                                "comments" to comments,
                                "isAdmin" to isAdmin
                            ),
                            "e"
                        )
                    )
                }
            }

            override fun removeArticle(articleId: Int) {
                launch {
                    appComponent.getModel().removeArticle(articleId)
                    call.respondRedirect("/admin")
                }
            }
        })
        val id = call.parameters["id"]!!.toIntOrNull()
        if (id == null)
            call.respond(HttpStatusCode.NotFound)
        else
            controller.start(id, call.parameters["action"])

    }

    route("create") {
        get {
            call.respond(FreeMarkerContent("create.ftl", mapOf("text" to "", "title" to "")))
        }
        post {
            val body = call.receiveParameters()
            val id = appComponent.getModel().createArticle(body["title"], body["text"])
            if (id.equals("-1"))
                call.respondRedirect("/articles/")
            else
                call.respondRedirect("/articles/$id")
        }
    }
}