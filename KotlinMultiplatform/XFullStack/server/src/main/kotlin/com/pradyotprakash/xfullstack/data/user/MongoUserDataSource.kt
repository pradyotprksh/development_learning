package com.pradyotprakash.xfullstack.data.user

import com.mongodb.client.model.Filters.eq
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.types.ObjectId
import utils.Constants.Database.Collections.USERS
import utils.Constants.DbKeys.EMAIL_ADDRESS
import utils.Constants.DbKeys.ID
import utils.Constants.DbKeys.PHONE_NUMBER

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
            eq(EMAIL_ADDRESS, email)
        ).limit(1).firstOrNull()
    }

    override suspend fun getUserByPhoneNumber(phoneNumber: String): User? {
        return usersCollection.find(
            eq(PHONE_NUMBER, phoneNumber)
        ).limit(1).firstOrNull()
    }

    override suspend fun getUserByUserId(userId: String): User? {
        return usersCollection.find(
            eq(ID, ObjectId(userId))
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
            eq(EMAIL_ADDRESS, email)
        ).toList().isNotEmpty()
    }

    override suspend fun isPhoneNumberPresent(phoneNumber: String): Boolean {
        return usersCollection.find(
            eq(PHONE_NUMBER, phoneNumber)
        ).toList().isNotEmpty()
    }
}