plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.project.pradyotprakash.twitter"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxCompose2.get()
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    dataBinding {
        isEnabled = true
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.compose.activity)
    implementation(libs.android.hilt)
    implementation(libs.androidx.navigation)
    implementation(libs.androidx.hilt.navigation)
    implementation(libs.corutines.android)
    implementation(libs.corutines.core)

    kapt(libs.android.hilt.compiler)

    implementation(projects.core.utils)
    implementation(projects.core.navigator)
    implementation(projects.feature.splash)
    implementation(projects.ui.twiteme)

    debugImplementation(libs.debug.compose.ui.tooling)
    debugImplementation(libs.debug.androidx.compose.ui)
}