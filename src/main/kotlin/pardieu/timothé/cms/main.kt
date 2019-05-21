package pardieu.timothé.cms

import de.mkammerer.argon2.Argon2Factory
import freemarker.cache.ClassTemplateLoader
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.*
import io.ktor.features.AutoHeadResponse
import io.ktor.features.DefaultHeaders
import io.ktor.freemarker.FreeMarker
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.content.files
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.sessions.*
import kotlinx.coroutines.launch
import pardieu.timothé.cms.model.Article
import pardieu.timothé.cms.presenter.ArticleListPresenter
import pardieu.timothé.cms.routes.apiRoutes
import pardieu.timothé.cms.routes.articlesRoutes
import pardieu.timothé.cms.routes.commentsRoutes
import pardieu.timothé.cms.tpl.IndexContext
import pardieu.timothé.cms.tpl.SampleSession


data class AuthenticatedUser(val name: String)

class App

fun main() {

    val appComponent = AppComponent("jdbc:mysql://localhost:8889/CMS?serverTimezone=UTC", "cms", "cms")

    embeddedServer(Netty, 8001) {
        install(AutoHeadResponse)
        install(DefaultHeaders) {
            header("X-Engine", "Ktor") // will send this header with each response
        }
        install(FreeMarker) {
            templateLoader = ClassTemplateLoader(App::class.java.classLoader, "templates")
        }
        install(Sessions) {
            cookie<SampleSession>("cookie")
        }
        install(Authentication) {
            form(name = "form_auth") {
                val argon2 = Argon2Factory.create()
                skipWhen { call -> call.sessions.get<SampleSession>() != null }
                userParamName = "name"
                passwordParamName = "password"
                challenge = FormAuthChallenge.Redirect { credentials -> "/login" }
                validate { credentials ->
                    try {
                        // Hash password
                        val usr = appComponent.getModel().getUserByUsername(credentials.name)
                        // Verify password
                        if (usr == null) {
                            null
                        } else {
                            if (argon2.verify(usr.password, credentials.password)) {
                                UserIdPrincipal(credentials.name)
                            } else {
                                null
                            }
                        }
                    } finally {
                        // Wipe confidential data
                        argon2.wipeArray(credentials.password.toCharArray())
                    }
//                    if (credentials.name == "admin" && credentials.password == "admin")

                }
            }
        }

        routing {
            static("static") {
                resources("static")
                files("images")
                files("css")
                files("js")
            }

            get("/") {
                call.respondRedirect("/articles/")
                // If the session was not set, or is invalid, the returned value is null.
                // val mySession: MySession? = call.sessions.get<MySession>()
            }

            authenticate("form_auth") {
                post("/login") {

                    val principal = call.authentication.principal<UserIdPrincipal>()
                    if (principal != null) {
                        println(principal)

                        //call.sessions.set(UserSession(principal.name))
                        call.sessions.set(SampleSession(name = "John", value = 12, isAdmin = true))
                        call.respondRedirect("/articles/1")
                    } else {
                        call.respondRedirect("/articles/")
                    }
                }
            }


            route("/articles") { articlesRoutes(appComponent) }
            route("/comments") { commentsRoutes(appComponent) }
            route("/login") {
                get {
                    call.respond(FreeMarkerContent("login.ftl", mapOf("name" to "", "password" to "")))
                }

            }

            route("/logout") {
                get {
                    if (call.sessions.get<SampleSession>() != null) {
                        call.sessions.clear<SampleSession>()
                        call.respondRedirect("/articles/")
                    }
                }
            }

            route("/register") {
                get {
                    call.respond(FreeMarkerContent("register.ftl", mapOf("username" to "", "password" to "")))

                }
                post {
                    val body = call.receiveParameters()
                    val password = body["password"]
                    val username = body["username"]!!
                    val argon2 = Argon2Factory.create()
                    val hash = argon2.hash(10, 65536, 1, password)
                    appComponent.getModel().register(username, hash)
                    call.respond(FreeMarkerContent("login.ftl", mapOf("name" to "", "password" to "")))
                }
            }


            route("/admin") {
                get {
                    val controller = appComponent.getArticleListPresenter(object : ArticleListPresenter.View {
                        override fun displayArticleList(list: List<Article>) {
                            val ctx = IndexContext(list, call.sessions.get<SampleSession>())
                            println(ctx.session)
                            launch {
                                call.respond(FreeMarkerContent("dashboard.ftl", ctx, "e"))
                            }
                        }
                    })
                    controller.start()
                }
            }
            route("api") { apiRoutes(appComponent) }

            /*    route("/"){
                    get("login"){
                        call.respond(FreeMarkerContent("login.ftl", mapOf("name" to "", "password" to "")))
                    }
                }


                authenticate {
                    post("login") {
                        val principal = call.principal<UserIdPrincipal>()
                        print(principal)
                        val result = if (principal != null) {
                            print("Principal not null")
                            /*dbQuery {
                                UserDao.select { UserDao.username eq principal.name }.firstOrNull()?.let {
                                    val profile = Profile(
                                        it[UserDao.id].toString()
                                    ).apply {
                                        username = it[UserDao.username]
                                        displayName = it[UserDao.name]
                                    }

                                    call.sessions.set(profile)

                                    HttpStatusCode.OK
                                } ?: HttpStatusCode.Unauthorized */
                            call.sessions.set(SampleSession(name = "John", value = 12))
                        } else {
                            print("Unauthorized")
                        }
                        HttpStatusCode.OK
                    }
                }*/
            /*route("/login") {
                authentication {
                    form {
                        validate { up: UserPasswordCredential ->
                            when {
                                up.password == "ppp" -> UserIdPrincipal(up.name)
                                else -> null
                            }
                        }
                    }
                }

                handle {
                    val principal = call.authentication.principal<UserIdPrincipal>()
                    if (principal != null) {
                        print("YYYYYYHUDHIZUHDUHUIHZDUHZDUIHUIZHDUHZUHDIUZDHU")
                        call.respondText("Hello, ${principal.name}")
                    } else {
                        print("FOOOOOOOOOOOOOOORM")
                        /* val html = createHTML().html {
                             body {
                                 form(
                                     action = "/login",
                                     encType = FormEncType.applicationXWwwFormUrlEncoded,
                                     method = FormMethod.post
                                 ) {
                                     p {
                                         +"user:"
                                         textInput(name = "user") {
                                             value = principal?.name ?: ""
                                         }
                                     }

                                     p {
                                         +"password:"
                                         passwordInput(name = "pass")
                                     }

                                     p {
                                         submitInput() { value = "Login" }
                                     }
                                 }
                             }
                         }
                         call.respondText(html, ContentType.Text.Html)*/
                        call.respond(FreeMarkerContent("login.ftl", mapOf("user" to "", "pass" to "")))

                    }
                }
            }*/
        }

        /*routing {
            route("/login") {
                authentication {
                    form {
                        validate { if (it.name == "test" && it.password == "password") UserIdPrincipal(it.name) else null }

                    }
                }

                handle {
                    val principal = call.authentication.principal<UserIdPrincipal>()
                    if (principal != null) {
                        print("OUIIIIIIIIIIIIIIIIIIII")
                        call.respondText("Hello, ${principal.name}")
                    } else {
                        print("NOOOOOOOON ENCULE")
                        val name = principal?.name ?: ""
                        call.respond(FreeMarkerContent("login.ftl", /*mapOf("name" to name, "password" to ""*/null , "e"))
                    }
                }
            }
        }*/
    }.start(wait = true)
}

