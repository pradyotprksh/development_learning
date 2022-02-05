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
    }
}