package com.pradyotprakash.findingfalcone.core.navigation

enum class Routes(
    val route: String,
    val arguments: List<String> = emptyList()
) {
    Splash("splash/"),
    Selector("selector/"),
    Result("result/{planets}/{vehicles}/{timeTaken}")
}