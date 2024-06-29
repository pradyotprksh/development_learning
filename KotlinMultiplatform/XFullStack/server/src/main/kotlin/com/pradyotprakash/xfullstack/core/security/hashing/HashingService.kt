package com.pradyotprakash.xfullstack.core.security.hashing

import utils.Constants.ConstValues.SALTED_LENGTH

interface HashingService {
    fun generateSaltedHash(value: String, saltLength: Int = SALTED_LENGTH): SaltedHash

    fun verify(value: String, saltedHash: SaltedHash): Boolean
}