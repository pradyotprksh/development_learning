import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * A set of all the configuration details which are needed while configuring the application
 */
object AndroidConfiguration {
    const val compileSdk = 32
    const val minSdk = 21
    const val targetSdk = 32

    const val applicationId = "com.project.pradyotprakash.kintagram"
    const val versionCode = 1

    val versionName = getVersionName()

    const val useSupportLibrary = true

    const val kotlinJVMTarget = "1.8"
    const val kotlinCompilerExtensionVersion = "1.0.1"

    const val excludeResources = "/META-INF/{AL2.0,LGPL2.1}"
}

/**
 * Generate version name
 */
private fun getVersionName(): String {
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-SSS")
    val formatted = current.format(formatter)
    return formatted ?: "1.0.0"
}