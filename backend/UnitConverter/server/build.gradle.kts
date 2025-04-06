plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinSerialization)
    application
}

group = "com.pradyotprakash.unitconverter"
version = "1.0.0"
application {
    mainClass.set("com.pradyotprakash.unitconverter.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.call.id)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.ktor.server.resources)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json.jvm)
    implementation(libs.kodein.di)
}