package example.com.data.database

import example.com.data.database.model.User
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class MongoRepository(
    db: CoroutineDatabase
) {
    private val users = db.getCollection<User>("AuthUsers")

    suspend fun insertUser(user: User): Boolean =
        users.insertOne(user).wasAcknowledged()

    suspend fun getUser(username: String): User? =
        users.findOne(User::username eq username)

}