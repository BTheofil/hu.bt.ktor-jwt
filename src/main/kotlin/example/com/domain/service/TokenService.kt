package example.com.domain.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import example.com.domain.model.TokenClaim
import example.com.domain.model.TokenConfig
import java.util.*

class TokenService {

    fun generate(config: TokenConfig, vararg claims: TokenClaim): String {
        var token = JWT.create()
            .withAudience(config.audience)
            .withIssuer(config.issuer)
            .withExpiresAt(Date(System.currentTimeMillis() + config.expiresIn))
        claims.forEach { claim ->
            token = token.withClaim(claim.name, claim.value)
        }
        return token.sign(Algorithm.HMAC256(config.secret))
    }
}