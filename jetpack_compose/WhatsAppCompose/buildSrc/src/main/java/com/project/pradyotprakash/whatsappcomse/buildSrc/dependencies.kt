package com.project.pradyotprakash.whatsappcomse.buildSrc

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:7.0.0"
    const val jdkDesugar = "com.android.tools:desugar_jdk_libs:1.1.5"
    const val material = "com.google.android.material:material:1.3.0"

    const val junit = "junit:junit:4.13.2"

    object Firebase {
        const val firebaseGradlePlugin = "com.google.gms:google-services:4.3.10"
        const val performanceGradlePlugin = "com.google.firebase:perf-plugin:1.4.0"
        const val crashlyticsGradlePlugin = "com.google.firebase:firebase-crashlytics-gradle:2.7.1"
        const val bom = "com.google.firebase:firebase-bom:28.3.1"
        const val analytics = "com.google.firebase:firebase-analytics-ktx"
        const val authentication = "com.google.firebase:firebase-auth-ktx"
        const val firestore = "com.google.firebase:firebase-firestore-ktx"
        const val crashlytics = "com.google.firebase:firebase-crashlytics-ktx"
        const val performance = "com.google.firebase:firebase-perf-ktx"
        const val storage = "com.google.firebase:firebase-storage-ktx"
        const val safetyNet = "com.google.firebase:firebase-appcheck-safetynet:16.0.0-beta02"
    }

    object Kotlin {
        private const val version = "1.5.21"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.6.0"

        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:1.3.1"
        }

        object Compose {
            const val version = "1.0.1"

            const val ui = "androidx.compose.ui:ui:$version"
            const val preview = "androidx.compose.ui:ui-tooling-preview:$version"
            const val liveData = "androidx.compose.runtime:runtime-livedata:$version"
            const val material = "androidx.compose.material:material:$version"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:1.0.0-beta02"
        }

        object Navigation {
            const val navigation = "androidx.navigation:navigation-compose:2.4.0-alpha06"
        }

        object Lifecycle {
            private const val version = "2.3.1"
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07"
        }
    }

    object ThirdParty {
        const val countryCodePicker = "com.sinaukoding:cccp:1.0.0"
    }
}
