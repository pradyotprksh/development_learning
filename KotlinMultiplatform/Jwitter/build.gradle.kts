@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application) apply (false)
    alias(libs.plugins.android.library) apply (false)
    alias(libs.plugins.jetbrains.compose) apply (false)
    kotlin("android").version(libs.versions.kotlin).apply(false)
    kotlin("multiplatform").version(libs.versions.kotlin).apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
