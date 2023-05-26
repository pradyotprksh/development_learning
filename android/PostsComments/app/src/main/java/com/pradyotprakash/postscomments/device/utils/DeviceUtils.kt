package com.pradyotprakash.postscomments.device.utils

import java.time.Instant

object DeviceUtils {
    fun getCurrentTimestamp(): Long {
        return Instant.now().toEpochMilli()
    }
}