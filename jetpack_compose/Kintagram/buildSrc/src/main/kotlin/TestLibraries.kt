/**
 * Test libraries which will be used in the whole project
 */
object TestLibraries {
    object Test {
        const val junit = "junit:junit:${Versions.Libraries.Test.junit}"
    }

    object AndroidTest {
        const val junit = "androidx.test.ext:junit:${Versions.Libraries.AndroidTest.junit}"
        const val espresso =
            "androidx.test.espresso:espresso-core:${Versions.Libraries.AndroidTest.espresso}"
        const val compose =
            "androidx.compose.ui:ui-test-junit4:${Versions.Libraries.AndroidX.compose}"
    }
}