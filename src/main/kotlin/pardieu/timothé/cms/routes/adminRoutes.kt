package pardieu.timothé.cms.routes

import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
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
import pardieu.timothé.cms.model.User
import pardieu.timothé.cms.presenter.ArticleListPresenter
import pardieu.timothé.cms.presenter.UserListPresenter
import pardieu.timothé.cms.tpl.IndexContext
import pardieu.timothé.cms.tpl.UserSession

fun Route.adminRoutes(appComponent: AppComponent) {
    get {
        val controller = appComponent.getArticleListPresenter(object : ArticleListPresenter.View {
            override fun displayArticleList(list: List<Article>) {
                val sess = call.sessions.get<UserSession>()
                val ctx = IndexContext(list, call.sessions.get<UserSession>())
                launch {
                    if (sess != null && sess.isAdmin)
                        call.respond(FreeMarkerContent("dashboard.ftl", ctx, "e"))
                    else
                        call.respondRedirect("/articles/")
                }
            }
        })
        controller.start()
    }
    get("users") {
        val controller = appComponent.getUserListPresenter(object : UserListPresenter.View {
            override fun displayUserList(list: List<User>) {
                val sess = call.sessions.get<UserSession>()
                launch {
                    if (sess != null && sess.isAdmin)
                        call.respond(
                            FreeMarkerContent(
                                "users.ftl",
                                mapOf("session" to sess, "list" to list),
                                "e"
                            )
                        )
                    else
                        call.respondRedirect("/articles/")
                }
            }
        })
        controller.start()
    }
    post("user/{id}") {
        val sess = call.sessions.get<UserSession>()
        val id = call.parameters["id"]!!.toIntOrNull()

        if (id != null && sess != null && sess.isAdmin) {
            val action = call.receiveParameters()["action"]
            when (action) {
                "upgrade" -> appComponent.getModel().upgradeUser(id)
                "downgrade" -> appComponent.getModel().downgradeUser(id)
                "delete" -> appComponent.getModel().deleteUser(id)
            }
            call.respondRedirect("/admin/users")

        } else {
            call.respondRedirect("/articles/")
        }
    }
}