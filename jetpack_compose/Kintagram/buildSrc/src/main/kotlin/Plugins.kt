/**
 * A list of all the plugins which will be used in the application
 */
object Plugins {
    object BuildPlugins {
        const val androidApplication = "com.android.application"
        const val jetbrainsKotlin = "org.jetbrains.kotlin.android"
        const val androidLibrary = "com.android.library"
        const val testRunnerInstrumentation = "androidx.test.runner.AndroidJUnitRunner"
        const val daggerHilt = "dagger.hilt.android.plugin"
    }

    object KotlinPlugins {
        const val kapt = "kapt"
    }
}