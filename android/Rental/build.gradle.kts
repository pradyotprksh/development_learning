plugins {
    id("com.android.application") version "7.3.0" apply false
    id("com.android.library") version "7.3.0" apply false
    id("org.jetbrains.kotlin.android") version "1.7.0" apply false
}

val baseUrl by rootProject.extra { "http://192.168.1.34:5000/" }

buildscript {
    repositories {
        google()
    }

    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.43.2")
        classpath("com.google.gms:google-services:4.3.14")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}