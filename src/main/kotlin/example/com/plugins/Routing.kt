package example.com.plugins

import example.com.route.getSecretInfo
import example.com.route.signIn
import example.com.route.signup
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureAuthRouting() {
    routing {
        signup()
        signIn()
        getSecretInfo()
    }
}
