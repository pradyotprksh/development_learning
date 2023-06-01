package com.zeller.terminalapp

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zeller.terminalapp.transactionList.TransactionsListView
import com.zeller.terminalapp.transactions.Transactions
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TransactionsListViewTest {
    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun teardown() {
        Intents.release()
    }

    @Test
    fun givenTransactionsListView_thenCorrectTransactionsShouldBeShown() {
        MainViewModel.transactions.addTransaction(Transactions(50.0f, true))
        MainViewModel.transactions.addTransaction(Transactions(25.0f, false))
        MainViewModel.transactions.addTransaction(Transactions(25.0f, false))

        launch(TransactionsListView::class.java).use {
            onView(withRecyclerView(R.id.transactionList).atPosition(0)).check(
                matches(
                    hasDescendant(
                        withText("+50.0")
                    )
                )
            );
            onView(withRecyclerView(R.id.transactionList).atPosition(1)).check(
                matches(
                    hasDescendant(
                        withText("-25.0")
                    )
                )
            );
            onView(withRecyclerView(R.id.transactionList).atPosition(2)).check(
                matches(
                    hasDescendant(
                        withText("-25.0")
                    )
                )
            );
        }
    }
}