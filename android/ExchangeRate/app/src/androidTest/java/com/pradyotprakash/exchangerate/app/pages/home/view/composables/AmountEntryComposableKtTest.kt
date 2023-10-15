package com.pradyotprakash.exchangerate.app.pages.home.view.composables

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.pradyotprakash.exchangerate.app.utils.ContentDescription.CURRENCY_MENU
import com.pradyotprakash.exchangerate.app.utils.ContentDescription.MENU_ITEM
import org.junit.Rule
import org.junit.Test

class AmountEntryComposableKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun checkAmountEntryComposableIfAllTheElementsAreRenderedCorrectly() {
        composeTestRule.setContent {
            AmountEntryComposable(
                amountText = "12",
                selectedCurrency = "AED",
                currencyMenuVisible = true,
                allCurrencies = mapOf(
                    "USD" to "",
                    "INR" to "",
                    "JPN" to ""
                ),
                updateAmountValue = {},
                updateMenuVisibility = { },
                updateSelectedCurrency = { _ -> }
            )
        }

        composeTestRule.onNodeWithText("12").assertExists()
        composeTestRule.onNodeWithText("AED").assertExists()
        composeTestRule.onNodeWithContentDescription(CURRENCY_MENU).assertExists()
        composeTestRule.onAllNodesWithContentDescription(MENU_ITEM).assertCountEquals(3)
        composeTestRule.onNodeWithText("USD").assertExists()
        composeTestRule.onNodeWithText("INR").assertExists()
        composeTestRule.onNodeWithText("JPN").assertExists()
    }
}