package com.pradyotprakash.xfullstack.core.helpers

import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import di.SharedModulesDi
import utils.Constants
import kotlin.random.Random

class UserHelper(
    private val userDataSource: UserDataSource,
    private val tweetDataSource: TweetDataSource,
) {
    suspend fun updateUserNature(
        userId: String,
    ) {
        val allTweetsEmotions = tweetDataSource.getUserTweetsEmotions(userId)

        val humanNature = SharedModulesDi.Instance.geminiRepository.getHumanNature(
            allTweetsEmotions.joinToString(", "), if (Random.nextBoolean()) {
                System.getenv(Constants.Keys.GEMINI_API_KEY_1)
            } else {
                System.getenv(Constants.Keys.GEMINI_API_KEY_2)
            }
        )

        if (humanNature.isNotEmpty()) {
            userDataSource.updateHumanNature(userId, humanNature)
        }
    }
}