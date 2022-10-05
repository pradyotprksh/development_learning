package com.project.pradyotprakash.rental.data.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.project.pradyotprakash.rental.core.services.FirestoreService

class FirestoreServiceRepository(
    private val firestore: FirebaseFirestore,
) : FirestoreService {
    override fun getRandomDocumentId(): String =
        firestore.collection("random").document().id
}