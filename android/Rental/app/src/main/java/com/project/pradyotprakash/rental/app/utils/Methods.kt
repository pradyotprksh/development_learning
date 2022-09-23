package com.project.pradyotprakash.rental.app.utils

fun isAllNotNull(vararg args: Any?, onNotNull: () -> Unit = {}, onNull: () -> Unit = {}) {
    when (args.filterNotNull().size) {
        args.size -> onNotNull()
        else -> onNull()
    }
}