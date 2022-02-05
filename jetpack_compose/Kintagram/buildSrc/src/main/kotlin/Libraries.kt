/**
 * A list of all the libraries which will be used in the whole application
 */
object Libraries {
    object Androidx {
        const val core = "androidx.core:core-ktx:${Versions.Libraries.AndroidX.core}"
        const val lifecycle =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Libraries.AndroidX.lifecycle}"

        object Compose {
            const val ui = "androidx.compose.ui:ui:${Versions.Libraries.AndroidX.compose}"
            const val material =
                "androidx.compose.material:material:${Versions.Libraries.AndroidX.compose}"
            const val toolingPreview =
                "androidx.compose.ui:ui-tooling-preview:${Versions.Libraries.AndroidX.compose}"
            const val uiTooling =
                "androidx.compose.ui:ui-tooling:${Versions.Libraries.AndroidX.compose}"
            const val activity =
                "androidx.activity:activity-compose:${Versions.Libraries.AndroidX.activityCompose}"
        }

        object Navigation {
            const val navFragment =
                "androidx.navigation:navigation-fragment-ktx:${Versions.Libraries.AndroidX.navigationGraph}"
            const val navUi =
                "androidx.navigation:navigation-ui-ktx:${Versions.Libraries.AndroidX.navigationGraph}"
            const val navDynamicFeature =
                "androidx.navigation:navigation-dynamic-features-fragment:${Versions.Libraries.AndroidX.navigationGraph}"
            const val navCompose =
                "androidx.navigation:navigation-compose:${Versions.Libraries.AndroidX.navCompose}"
        }
    }

    object Google {
        const val hilt = "com.google.dagger:hilt-android:${Versions.Plugins.hiltPlugin}"
        const val hiltCompiler =
            "com.google.dagger:hilt-android-compiler:${Versions.Plugins.hiltPlugin}"
    }

    object Kotlin {
        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Libraries.Kotlin.coroutines}"
    }
}