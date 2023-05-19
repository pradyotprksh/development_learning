@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application) apply(false)
    alias(libs.plugins.android.library) apply(false)
    alias(libs.plugins.jetbrains.kotlin) apply(false)
    alias(libs.plugins.hilt) apply(false)
}

val baseUrl by rootProject.extra { "https://findfalcone.geektrust.com/" }