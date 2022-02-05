plugins {
    id(BuildPlugins.androidApplication) version Versions.Plugins.androidApplication apply false
    id(BuildPlugins.androidLibrary) version Versions.Plugins.androidApplication apply false
    id(BuildPlugins.jetbrainsKotlin) version Versions.Plugins.kotlin apply false
}

task<Delete>("delete") {
    delete(rootProject.buildDir)
}