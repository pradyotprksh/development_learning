package com.pradyotprakash.xfullstack.data.file

import io.github.jan.supabase.storage.Storage
import java.io.File

class SupabaseFileDataSource(
    private val storage: Storage,
) : FileDataSource {
    override suspend fun isBucketPresent(id: String): Boolean {
        return try {
            storage.retrieveBucketById(id) != null
        } catch (_: Exception) {
            return false
        }
    }

    override suspend fun uploadFile(id: String, fileName: String, file: File): String {
        storage.from(id).upload(fileName, file.readBytes(), upsert = true)
        return storage.from(id).publicUrl(fileName)
    }
}