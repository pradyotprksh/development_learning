/**
 * Versions available for all the plugins/libraries in the project
 */
object Versions {
    object Plugins {
        const val kotlin = "1.5.21"
        const val androidApplication = "7.1.0"
    }

    object Libraries {
        object AndroidX {
            const val core = "1.7.0"
            const val compose = "1.0.5"
            const val lifecycle = "2.4.0"
            const val activityCompose = "1.4.0"
        }

        object Test {
            const val junit = "4.13.2"
        }

        object AndroidTest {
            const val junit = "1.1.3"
            const val espresso = "3.4.0"
        }
    }
}