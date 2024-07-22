package example.com.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import example.com.domain.model.TokenConfig
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import org.koin.ktor.ext.inject

fun Application.configureSecurity() {

    val token by inject<TokenConfig>()

    authentication {
        jwt {
            realm = this@configureSecurity.environment.config.property("jwt.realm").getString()
            verifier(
                JWT.require(Algorithm.HMAC256(token.secret))
                    .withAudience(token.audience)
                    .withIssuer(token.issuer)
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(token.audience)) {
                    JWTPrincipal(credential.payload)
                } else null
            }
        }
    }
}
