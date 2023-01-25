import com.android.build.gradle.internal.tasks.factory.dependsOn

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.firebase.firebase-perf")
    kotlin("kapt")
}

tasks.register("updateTranslations") {
    Runtime.getRuntime().exec("python3 ./script/generate_translation_file.py")
}

tasks.register<de.undercouch.gradle.tasks.download.Download>("checkEnvironment") {
    src("${rootProject.extra.get("baseUrl")}/renter")
    dest("$rootDir")
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.project.pradyotprakash.rental"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField(
            name = "baseUrl",
            value = "\"${rootProject.extra.get("baseUrl").toString()}\"",
            type = "String"
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
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
        enable = true
    }

    project.tasks.preBuild.dependsOn("checkEnvironment")
    project.tasks.preBuild.dependsOn("updateTranslations")

    namespace = "com.project.pradyotprakash.rental"
}

kapt {
    correctErrorTypes = true
}

dependencies {
    // Activity
    implementation("androidx.activity:activity-compose:1.6.1")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.4")

    // Core
    implementation("androidx.core:core-ktx:1.9.0")

    // Compose
    implementation("androidx.compose.ui:ui:1.3.3")
    implementation("androidx.compose.material3:material3:1.0.1")
    implementation("androidx.compose.material:material:1.3.1")
    implementation("androidx.compose.material3:material3-window-size-class:1.0.1")
    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation("androidx.compose.runtime:runtime-livedata:1.3.3")
    debugImplementation("androidx.compose.ui:ui-tooling:1.3.3")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.3.3")
    implementation("com.google.accompanist:accompanist-testharness:0.28.0")

    // Dependency Injection
    implementation("com.google.dagger:hilt-android:2.44")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.10")
    debugImplementation("com.github.chuckerteam.chucker:library:3.5.2")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:3.5.2")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:30.5.0"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-crashlytics-ktx")
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-perf-ktx")
    implementation("com.google.firebase:firebase-storage-ktx")
    implementation("com.google.firebase:firebase-appcheck-playintegrity")
    implementation("com.firebaseui:firebase-ui-auth:8.0.2")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.3.3")

    // Chrome browser
    implementation("androidx.browser:browser:1.4.0")

    // Permissions
    implementation("com.google.accompanist:accompanist-permissions:0.25.0")

    // Location
    implementation("com.google.android.gms:play-services-location:21.0.1")

    // Coil
    implementation("io.coil-kt:coil-compose:2.2.2")

    // Time ago
    implementation("com.github.marlonlom:timeago:4.0.3")

    // Logger
    implementation("com.orhanobut:logger:2.2.0")

    // Leak Canary
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.9.1")
}