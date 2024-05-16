package com.pradyotprakash.xfullstack.data.user

import com.mongodb.client.model.Filters.eq
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import utils.Constants.Database.Collections.USERS
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.types.ObjectId

class MongoUserDataSource(
    db: MongoDatabase,
) : UserDataSource {
    private val usersCollection = db.getCollection<User>(USERS)

    override suspend fun getUserByUsername(username: String): User? {
        return usersCollection.find(
            eq(User::username.name, username)
        ).limit(1).firstOrNull()
    }

    override suspend fun getUserByEmailAddress(email: String): User? {
        return usersCollection.find(
            eq(User::emailAddress.name, email)
        ).limit(1).firstOrNull()
    }

    override suspend fun getUserByPhoneNumber(phoneNumber: String): User? {
        return usersCollection.find(
            eq(User::phoneNumber.name, phoneNumber)
        ).limit(1).firstOrNull()
    }

    override suspend fun getUserByUserId(userId: String): User? {
        return usersCollection.find(
            eq("_id", ObjectId(userId))
        ).limit(1).firstOrNull()
    }

    override suspend fun insertNewUser(user: User): Boolean {
        return usersCollection.insertOne(user).wasAcknowledged()
    }

    override suspend fun isUsernamePresent(username: String): Boolean {
        return usersCollection.find(
            eq(User::username.name, username)
        ).toList().isNotEmpty()
    }

    override suspend fun isEmailPresent(email: String): Boolean {
        return usersCollection.find(
            eq(User::emailAddress.name, email)
        ).toList().isNotEmpty()
    }

    override suspend fun isPhoneNumberPresent(phoneNumber: String): Boolean {
        return usersCollection.find(
            eq(User::phoneNumber.name, phoneNumber)
        ).toList().isNotEmpty()
    }
}