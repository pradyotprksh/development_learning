package com.pradyotprakash.xfullstack.data.file

import java.io.File

interface FileDataSource {
    suspend fun isBucketPresent(id: String): Boolean

    suspend fun uploadFile(id: String, fileName: String, file: File): String
}