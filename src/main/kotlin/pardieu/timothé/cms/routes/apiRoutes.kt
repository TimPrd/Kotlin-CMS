package pardieu.timothé.cms.routes

import com.google.gson.Gson
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get
import kotlinx.coroutines.launch
import pardieu.timothé.cms.AppComponent
import pardieu.timothé.cms.presenter.ArticleListPresenter
import pardieu.timothé.cms.presenter.ArticlePresenter
import pardieu.timothé.cms.model.Article
import pardieu.timothé.cms.model.Comment

fun Route.apiRoutes(appComponent: AppComponent) {
    get("articles") {
        appComponent.getArticleListPresenter(object : ArticleListPresenter.View {
            override fun displayArticleList(list: List<Article>) {
                launch {
                    val json = Gson().toJson(list)
                    call.respondText(json, ContentType.Application.Json)
                }
            }
        }).start()

    }

    get("article/{id}") {
        val id = call.parameters["id"]!!.toIntOrNull()
        if (id == null)
            call.respond(HttpStatusCode.NotFound)
        else
            appComponent.getArticlePresenter(object : ArticlePresenter.View {
                override fun displayArticle(
                    article: Article?,
                    comments: List<Comment>?
                ) {
                    launch {
                        call.respondText(Gson().toJson(article), ContentType.Application.Json)
                    }
                }

                override fun displayArticleNotFound() {
                    launch {
                        call.respond(HttpStatusCode.NotFound)
                    }
                }
            }).start(id)

    }
}