# Android Coding Challenge

## Scenario
Imagine you have just joined a mobile team that is currently working on a banking application.
The application is live on the Play Store, and the CI/CD is set up to build a new version and push to the Play Store on every push to the repository.

Currently, the banking application allows its users to deposit and withdraw money from their bank account. 
If the user does not have enough money, the app prevents them from making that withdrawal.

Example: Deposit:50.00(ok), Withdraw:25.00(ok), Withdraw:25.00(ok), Withdraw:0.1(not ok)

Note: This feature is currently implemented locally, the API to support this feature is still being worked on by the backend team but should be available in the near future.   

## Your tasks
- Introduce a new feature that allows the user to view their past transactions
- Improve the current code which was intentionally made with poor programming practices and a few bugs
- Improvements are to be made without rewriting the app

## Expectations
The code is simple enough for one person to easily refactor in a couple sittings, but what we're after is seeing your refactoring strategy.
We also don't expect the you to refactor everything, instead, we'd like you to tell us what you found important to change and why.
We expect that the repository is always at a releasable state. It is important to make sure that any change does not introduce any regressions or bugs.

We understand that everybody's situation is different that's why we give candidates several days to complete the exercise. 
Having said that, please don't spend more than a usual working day to complete this exercise.

Provide a README file to document any decisions/improvements you've deferred until later and any assumptions you have made.

### What we're looking for
- Small incremental improvements, commit in small deliverable chunks
- Show how you architect new code with a focus on testability
- A testing strategy to support the refactoring
    - Think about how you might prove you are not introducing regressions
- Bonus marks for UI tests
- Proper modelling of the domain, understand we are dealing with people's money
- A README that will help other developers

### What you will be submitting
- A zip file of the entire project including the .git folder -- the git history is an important part of this submission
- A README that details the decisions you've made. This is a good place to write which improvements you've deferred but would like to work on given more time.

## Questions?
- All questions are good questions, if you have any, please don't hesitate to send us an e-mail at tim@myzeller.com and chris.hatton@myzeller.com

Good luck and happy coding!
