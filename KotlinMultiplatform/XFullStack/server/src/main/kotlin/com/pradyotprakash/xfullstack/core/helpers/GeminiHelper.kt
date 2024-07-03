package com.pradyotprakash.xfullstack.core.helpers

import utils.Constants
import kotlin.random.Random

class GeminiHelper {
    fun getGeminiAPIKey() = if (Random.nextBoolean()) {
        System.getenv(Constants.Keys.GEMINI_API_KEY_1)
    } else {
        System.getenv(Constants.Keys.GEMINI_API_KEY_2)
    }
}