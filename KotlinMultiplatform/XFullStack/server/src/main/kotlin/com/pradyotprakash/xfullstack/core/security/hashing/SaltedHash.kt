package com.pradyotprakash.xfullstack.core.security.hashing

data class SaltedHash(
    val hash: String,
    val salt: String,
)
