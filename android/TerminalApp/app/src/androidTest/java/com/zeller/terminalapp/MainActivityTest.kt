package com.zeller.terminalapp

import android.view.KeyEvent
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressKey
import androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zeller.terminalapp.main.MainActivity
import com.zeller.terminalapp.transactionList.TransactionsListView
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun teardown() {
        Intents.release()
    }


    @Test
    fun givenMainActivity_inputsResultsInCorrectResult() {
        launch(MainActivity::class.java).use {
            inputAmount("50")
            onView(withId(R.id.depositButton)).perform(click())
            onView(withId(R.id.balance)).check(matches(withText("50.0")))
            clearInput("50")

            assert(MainViewModel.balance == 50.0f)
            assert(MainViewModel.transactions.getTransactions().size == 1)

            inputAmount("abc")
            onView(withId(R.id.depositButton)).perform(click())
            onView(withId(R.id.balance)).check(matches(withText("50.0")))
            clearInput("abc")

            assert(MainViewModel.balance == 50.0f)
            assert(MainViewModel.transactions.getTransactions().size == 1)

            inputAmount("25")
            onView(withId(R.id.withdrawButton)).perform(click())
            onView(withId(R.id.balance)).check(matches(withText("25.0")))
            clearInput("25")

            assert(MainViewModel.balance == 25.0f)
            assert(MainViewModel.transactions.getTransactions().size == 2)

            inputAmount("25")
            onView(withId(R.id.withdrawButton)).perform(click())
            onView(withId(R.id.balance)).check(matches(withText("0.0")))
            clearInput("25")

            assert(MainViewModel.balance == 0.0f)
            assert(MainViewModel.transactions.getTransactions().size == 3)

            inputAmount("0.1")
            onView(withId(R.id.withdrawButton)).perform(click())
            onView(withId(R.id.balance)).check(matches(withText("0.0")))
            clearInput("0.1")

            assert(MainViewModel.balance == 0.0f)
            assert(MainViewModel.transactions.getTransactions().size == 3)

            onView(withId(R.id.historyButton)).perform(click())
            intended(hasComponent(TransactionsListView::class.java.name))
        }
    }

    private fun clearInput(value: String) {
        for (i in 0..value.length) {
            onView(withId(R.id.amountInput))
                .perform(pressKey(KeyEvent.KEYCODE_DEL))
        }
    }

    private fun inputAmount(amount: String) {
        onView(withId(R.id.amountInput)).perform(click())
        onView(withId(R.id.amountInput)).perform(typeTextIntoFocusedView(amount))
        Espresso.pressBack()
    }
}