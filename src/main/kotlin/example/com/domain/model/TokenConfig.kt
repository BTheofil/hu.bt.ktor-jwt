package example.com.domain.model

data class TokenConfig(
    val audience: String,
    val issuer: String,
    val expiresIn: Long,
    val secret: String
)
