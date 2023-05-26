package com.pradyotprakash.postscomments.device

import java.time.Instant

object DeviceUtils {
    fun getCurrentTimestamp(): Long {
        return Instant.now().toEpochMilli()
    }
}