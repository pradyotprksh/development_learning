package com.pradyotprakash.exchangerate.app.pages.home.view.composables

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.pradyotprakash.exchangerate.app.utils.ContentDescription.EXCHANGE_RATE
import org.junit.Rule
import org.junit.Test

class ExchangeRateComposableKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun checkExchangeRateComposableIfAllTheElementsAreRenderedCorrectly() {
        var countryName = ""

        composeTestRule.setContent {
            ExchangeRateComposable(
                showCountryName = { name -> countryName = name },
                countryCode = "INR",
                countryName = "India",
                rate = 12.12345
            )
        }

        composeTestRule.onNodeWithText("INR").assertExists()
        composeTestRule.onNodeWithText("12.12").assertExists()
        composeTestRule.onNodeWithContentDescription(EXCHANGE_RATE).assertExists().performClick()
        assert(countryName == "India (INR)")
    }
}