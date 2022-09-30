package com.project.pradyotprakash.rental.app.utils

fun Int.toProgress() : Float {
    return when (this) {
        in 0..10 -> 0.0f
        in 11..20 -> 0.1f
        in 21..30 -> 0.2f
        in 31..40 -> 0.3f
        in 41..50 -> 0.4f
        in 51..60 -> 0.5f
        in 61..70 -> 0.6f
        in 71..80 -> 0.7f
        in 81..90 -> 0.8f
        in 91..100 -> 0.9f
        else -> 1.0f
    }
}