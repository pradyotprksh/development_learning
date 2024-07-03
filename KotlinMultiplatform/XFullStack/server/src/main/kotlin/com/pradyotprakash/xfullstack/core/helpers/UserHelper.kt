package com.pradyotprakash.xfullstack.core.helpers

import com.pradyotprakash.xfullstack.data.tweet.TweetDataSource
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import di.SharedModulesDi

class UserHelper(
    private val userDataSource: UserDataSource,
    private val tweetDataSource: TweetDataSource,
    private val geminiHelper: GeminiHelper,
) {
    suspend fun updateUserNature(
        userId: String,
    ) {
        val allTweetsEmotions = tweetDataSource.getUserTweetsEmotions(userId)

        val humanNature = SharedModulesDi.Instance.geminiRepository.getHumanNature(
            allTweetsEmotions.joinToString(", "), geminiHelper.getGeminiAPIKey(),
        )

        if (humanNature.isNotEmpty()) {
            userDataSource.updateHumanNature(userId, humanNature)
        }
    }
}