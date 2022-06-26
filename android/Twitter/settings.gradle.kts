pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Twitter"

enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

// App
include(":app")

// Core
include(
    ":core:utils",
    ":core:authentication",
    ":core:navigator"
)

// Feature
include(
    ":feature:authentication:options",
    ":feature:authentication:register",
    ":feature:authentication:login",
    ":feature:home"
)
