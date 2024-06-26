package com.pradyotprakash.xfullstack.core.database

import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import org.bson.BsonInt64
import org.bson.Document
import utils.Constants.Database.DATABASE_NAME
import utils.Constants.Database.DEFAULT_MONGODB_URI
import utils.Constants.Database.MONGODB_URI
import utils.Logger
import utils.LoggerLevel

object XFullStackMongoDBClient {
    private val connectString = if (System.getenv(MONGODB_URI) != null) {
        System.getenv(MONGODB_URI)
    } else {
        DEFAULT_MONGODB_URI
    }
    private val client = MongoClient.create(connectionString = connectString)
    private val database = client.getDatabase(DATABASE_NAME)

    fun getDatabase(): MongoDatabase = database

    /**
     * Checks if the database is available or not.
     */
    suspend fun isDatabaseAvailable(): MongoDatabase? {
        return try {
            val command = Document("ping", BsonInt64(1))
            database.runCommand(command)
            database
        } catch (e: Exception) {
            Logger.log(
                loggerLevel = LoggerLevel.Error,
                message = "Error connecting to DB. Details:\n$e"
            )
            null
        }
    }
}