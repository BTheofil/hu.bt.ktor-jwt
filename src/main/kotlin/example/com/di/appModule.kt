package example.com.di

import example.com.data.database.MongoRepository
import example.com.domain.service.SHA256HashingService
import example.com.domain.service.TokenService
import org.koin.dsl.module
import org.litote.kmongo.reactivestreams.KMongo
import org.litote.kmongo.coroutine.coroutine

val appModule = module {

    single {
        val pw = System.getenv("MONGO_PW")
        KMongo.createClient(
            "mongodb+srv://jwt-auth:$pw@cluster0.wtbp8u6.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0"
        ).coroutine
            .getDatabase("sample_mflix")
    }

    single { MongoRepository(get()) }

    single { TokenService() }

    single { SHA256HashingService() }
}