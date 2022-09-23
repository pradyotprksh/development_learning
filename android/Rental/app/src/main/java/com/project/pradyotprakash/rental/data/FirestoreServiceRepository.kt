package com.project.pradyotprakash.rental.data

import com.google.firebase.firestore.FirebaseFirestore
import com.project.pradyotprakash.rental.domain.services.FirestoreService

class FirestoreServiceRepository(
    private val firestore: FirebaseFirestore,
) : FirestoreService {
    override fun getRandomDocumentId(): String =
        firestore.collection("random").document().id
}