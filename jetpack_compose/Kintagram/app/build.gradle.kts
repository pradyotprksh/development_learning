plugins {
    id(Plugins.BuildPlugins.androidApplication)
    id(Plugins.BuildPlugins.jetbrainsKotlin)
    id(Plugins.BuildPlugins.daggerHilt)

    kotlin(Plugins.KotlinPlugins.kapt)
}

android {
    compileSdk = AndroidConfiguration.compileSdk

    defaultConfig {
        applicationId = AndroidConfiguration.applicationId
        minSdk = AndroidConfiguration.minSdk
        targetSdk = AndroidConfiguration.targetSdk
        versionCode = AndroidConfiguration.versionCode
        versionName = AndroidConfiguration.versionName

        testInstrumentationRunner = Plugins.BuildPlugins.testRunnerInstrumentation
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

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(Libraries.Androidx.core)
    implementation(Libraries.Androidx.lifecycle)
    implementation(Libraries.Androidx.Compose.ui)
    implementation(Libraries.Androidx.Compose.material)
    implementation(Libraries.Androidx.Compose.toolingPreview)
    implementation(Libraries.Androidx.Compose.activity)
    implementation(Libraries.Androidx.Navigation.navFragment)
    implementation(Libraries.Androidx.Navigation.navUi)
    implementation(Libraries.Androidx.Navigation.navDynamicFeature)
    implementation(Libraries.Androidx.Navigation.navCompose)
    implementation(Libraries.Google.hilt)
    implementation(Libraries.Kotlin.coroutines)

    debugImplementation(Libraries.Androidx.Compose.uiTooling)

    testImplementation(TestLibraries.Test.junit)

    androidTestImplementation(TestLibraries.AndroidTest.junit)
    androidTestImplementation(TestLibraries.AndroidTest.espresso)
    androidTestImplementation(TestLibraries.AndroidTest.compose)
    androidTestImplementation(TestLibraries.AndroidTest.navigationCompose)

    kapt(Libraries.Google.hiltCompiler)
}