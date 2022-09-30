package com.project.pradyotprakash.rental.data

import android.graphics.Bitmap
import com.google.firebase.storage.StorageReference
import com.project.pradyotprakash.rental.app.utils.toProgress
import com.project.pradyotprakash.rental.domain.services.StorageService
import java.io.ByteArrayOutputStream

class StorageServiceRepository : StorageService {
    override fun uploadFile(
        bitmap: Bitmap,
        storageReference: StorageReference,
        fileName: String,
        onSuccess: (String) -> Unit,
        onFailure: (Exception) -> Unit,
        onProgress: (Float) -> Unit,
    ) {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()
        val uploadTask = storageReference.child(fileName).putBytes(data)
        uploadTask.addOnFailureListener {
            onFailure(it)
        }.addOnSuccessListener {
            storageReference.downloadUrl.addOnSuccessListener {
                onSuccess(it.toString())
            }.addOnFailureListener {
                onFailure(it)
            }
        }.addOnProgressListener {
            val progress = ((100 * it.bytesTransferred) / it.totalByteCount).toInt().toProgress()
            onProgress(progress)
        }
    }
}