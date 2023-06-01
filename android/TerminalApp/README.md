# TerminalApp

## Decisions

1. Upgraded the libraries and gradle version to the latest, was facing Java version conflict

## Issues or Refactoring found

1. Issue : If the current amount is equal to the withdrawing amount then it still shows "Not enough
   balance".
2. Refactor: Re-writing the same code for amount check
3. Refactor: Instead of if condition use when for better understanding
4. Refactor: Files should be placed in different packages for better understanding
5. Refactor: 

## Actions or Changes done

1. Fixed the amount check issue
2. Moved files to different packages

## Implementation

1. Create TransactionsListView for showing the list of transactions done, because transactionsList
   is set as private
2. Added recycler view for showing the transactions

## Tests

1. Tests for main screen
    1. For input amount
    2. Deposit & Withdrawal logic
    3. History screen
    4. Transaction list and balance is updated correctly
2. Tests for transaction view
    1. Showing the correct transaction or not

Run all the tests using `./gradlew connectedAndroidTest`