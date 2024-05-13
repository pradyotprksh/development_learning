package com.pradyotprakash.xfullstack.data.user

import com.mongodb.client.model.Filters.eq
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import com.pradyotprakash.xfullstack.utils.Constants.Database.Collections.USERS
import kotlinx.coroutines.flow.firstOrNull

class MongoUserDataSource(
    db: MongoDatabase,
) : UserDataSource {
    private val usersCollection = db.getCollection<User>(USERS)

    override suspend fun getUserByUsername(username: String): User? {
        return usersCollection.find(
            eq(User::username.name, username)
        ).limit(1).firstOrNull()
    }

    override suspend fun insertNewUser(user: User): Boolean {
        return usersCollection.insertOne(user).wasAcknowledged()
    }
}