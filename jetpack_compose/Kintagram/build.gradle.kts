plugins {
    id(Plugins.BuildPlugins.androidApplication) version Versions.Plugins.androidApplication apply false
    id(Plugins.BuildPlugins.androidLibrary) version Versions.Plugins.androidApplication apply false
    id(Plugins.BuildPlugins.jetbrainsKotlin) version Versions.Plugins.kotlin apply false
}

buildscript {
    repositories {
        google()
    }

    dependencies {
        classpath(GradleLibraries.hiltPlugin)
        classpath("com.android.tools.build:gradle:7.1.1")
    }
}

task<Delete>("delete") {
    delete(rootProject.buildDir)
}