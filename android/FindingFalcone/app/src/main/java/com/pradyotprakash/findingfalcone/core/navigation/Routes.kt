package com.pradyotprakash.findingfalcone.core.navigation

enum class Routes(
    val route: String,
) {
    Splash("splash/"),
    Selector("selector/"),
    Result("result/{planets}/{vehicles}/{timeTaken}")
}