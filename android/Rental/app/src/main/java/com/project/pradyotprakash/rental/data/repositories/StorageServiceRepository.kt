package com.project.pradyotprakash.rental.data.repositories

import android.graphics.Bitmap
import com.google.firebase.storage.StorageReference
import com.orhanobut.logger.Logger
import com.project.pradyotprakash.rental.app.utils.toProgress
import com.project.pradyotprakash.rental.core.services.CrashlyticsService
import com.project.pradyotprakash.rental.core.services.StorageService
import java.io.ByteArrayOutputStream

class StorageServiceRepository(
    private val crashlyticsService: CrashlyticsService,
) : StorageService {
    override fun uploadFile(
        bitmap: Bitmap,
        storageReference: StorageReference,
        fileName: String,
        onSuccess: (String) -> Unit,
        onFailure: (Exception) -> Unit,
        onProgress: (Float) -> Unit,
    ) {
        try {
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()
            val uploadTask = storageReference.child(fileName).putBytes(data)
            uploadTask.addOnFailureListener {
                onFailure(it)
            }.addOnSuccessListener {
                it.storage.downloadUrl.addOnSuccessListener { uri ->
                    onSuccess(uri.toString())
                }.addOnFailureListener { exception ->
                    onFailure(exception)
                }
            }.addOnProgressListener {
                val progress = ((100 * it.bytesTransferred) / it.totalByteCount).toInt().toProgress()
                onProgress(progress)
            }
        } catch (e: Exception) {
            Logger.e(e.toString())
            crashlyticsService.submitCaughtException(e)
            onFailure(e)
        }
    }
}