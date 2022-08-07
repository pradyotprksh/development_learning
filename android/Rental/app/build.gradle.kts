plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.project.pradyotprakash.rental"
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
        kotlinCompilerExtensionVersion = "1.2.0"
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
    // Activity
    implementation("androidx.activity:activity-compose:1.5.1")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.3")

    // Core
    implementation("androidx.core:core-ktx:1.8.0")

    // Compose
    implementation("androidx.compose.ui:ui:1.2.0")
    implementation("androidx.compose.material3:material3:1.0.0-alpha15")
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.0")
    implementation("androidx.navigation:navigation-compose:2.5.1")
    debugImplementation("androidx.compose.ui:ui-tooling:1.2.0")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.2.0")

    // Dependency Injection
    implementation("com.google.dagger:hilt-android:2.43.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    kapt("com.google.dagger:hilt-android-compiler:2.43.1")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.2.0")
}