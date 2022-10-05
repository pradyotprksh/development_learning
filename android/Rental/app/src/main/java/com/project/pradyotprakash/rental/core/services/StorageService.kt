package com.project.pradyotprakash.rental.core.services

import android.graphics.Bitmap
import com.google.firebase.storage.StorageReference
import java.util.*

interface StorageService {
    fun uploadFile(
        bitmap: Bitmap,
        storageReference: StorageReference,
        fileName: String = UUID.randomUUID().toString(),
        onSuccess: (String) -> Unit = { _ ->  },
        onFailure: (Exception) -> Unit = { _ ->  },
        onProgress: (Float) -> Unit = { _ ->  },
    )
}