package com.project.pradyotprakash.rental.domain.modal

data class DefaultEntity<T>(
    val code: Int,
    val message: String,
    val data: T? = null,
)