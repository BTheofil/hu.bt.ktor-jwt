package example.com

import example.com.di.appModule
import example.com.domain.model.TokenConfig
import example.com.plugins.*
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused")
fun Application.module() {

    install(Koin) {
        slf4jLogger()
        modules(
            appModule,
            org.koin.dsl.module {
                single {
                    TokenConfig(
                        issuer = environment.config.property("jwt.issuer").getString(),
                        audience = environment.config.property("jwt.audience").getString(),
                        expiresIn = 1000L * 60L * 60L, //1hour
                        secret = System.getenv("JWT_SECRET")
                    )
                }
            }
        )
    }

    configureSerialization()
    configureMonitoring()
    configureHTTP()
    configureSecurity()
    configureAuthRouting()
}
