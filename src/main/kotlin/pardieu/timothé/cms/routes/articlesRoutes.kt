package pardieu.timothé.cms.routes

import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import kotlinx.coroutines.launch
import pardieu.timothé.cms.AppComponent
import pardieu.timothé.cms.model.Article
import pardieu.timothé.cms.model.Comment
import pardieu.timothé.cms.presenter.ArticleListPresenter
import pardieu.timothé.cms.presenter.ArticlePresenter
import pardieu.timothé.cms.tpl.IndexContext

fun Route.articlesRoutes(appComponent: AppComponent) {

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
                    call.respond(FreeMarkerContent("article.ftl", mapOf("article" to article, "comments" to comments), "e"))
                }
            }
        })
        val id = call.parameters["id"]!!.toIntOrNull()
        if (id == null)
            call.respond(HttpStatusCode.NotFound)
        else
            controller.start(id)

    }

    get("create") {
        call.respond(FreeMarkerContent("create.ftl", mapOf("text" to "", "title" to "")))
    }
}

/*       post("create") {
           val body = call.receiveParameters()
           val id = model.createArticle(body["title"], body["text"])
           if (id.equals("-1"))
               call.respondRedirect("/articles/")
           else
               call.respondRedirect("/articles/$id")
       }*/