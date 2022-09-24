package com.project.pradyotprakash.rental.domain.modal

data class DefaultEntity<T>(
    val code: Int,
    val message: String,
    val count: Int = 0,
    val data: T? = null,
)