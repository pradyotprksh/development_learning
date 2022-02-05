plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.jetbrainsKotlin)
}

android {
    compileSdk = AndroidConfiguration.compileSdk

    defaultConfig {
        applicationId = AndroidConfiguration.applicationId
        minSdk = AndroidConfiguration.minSdk
        targetSdk = AndroidConfiguration.targetSdk
        versionCode = AndroidConfiguration.versionCode
        versionName = AndroidConfiguration.versionName

        testInstrumentationRunner = BuildPlugins.testRunnerInstrumentation
        vectorDrawables {
            useSupportLibrary = AndroidConfiguration.useSupportLibrary
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = AndroidConfiguration.kotlinJVMTarget
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = AndroidConfiguration.kotlinCompilerExtensionVersion
    }

    packagingOptions {
        resources {
            excludes += AndroidConfiguration.excludeResources
        }
    }
}

dependencies {
    implementation(Libraries.Androidx.core)
    implementation(Libraries.Androidx.Compose.ui)
    implementation(Libraries.Androidx.Compose.material)
    implementation(Libraries.Androidx.Compose.toolingPreview)
    implementation(Libraries.Androidx.lifecycle)
    implementation(Libraries.Androidx.Compose.activity)

    debugImplementation(Libraries.Androidx.Compose.uiTooling)

    testImplementation(TestLibraries.Test.junit)

    androidTestImplementation(TestLibraries.AndroidTest.junit)
    androidTestImplementation(TestLibraries.AndroidTest.espresso)
    androidTestImplementation(TestLibraries.AndroidTest.compose)
}