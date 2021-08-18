package com.project.pradyotprakash.whatsappcompose.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.project.pradyotprakash.whatsappcompose.models.User

/**
 * A utility class which will connect to Firestore and perform all the operation required.
 *
 * All the communication will happen from here only. This way it will be easy to maintain and
 * modify the database operations.
 */
class FirestoreUtility {
    private val db = Firebase.firestore
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    /**
     * Checks if the user details are available or not
     */
    fun isUserDetailsAvailable(): Boolean {
        if (firebaseAuth.currentUser != null) {
            val userId = getCurrentUserId()
            return db.collection(DBConstants.Collection.users).document(userId)
                .get().result.exists()
        }
        return false
    }

    /**
     * Get the current user id from firebase
     */
    private fun getCurrentUserId(): String {
        return firebaseAuth.currentUser!!.uid
    }

    /**
     * Get user details for the current user
     */
    fun getCurrentUserDetails() : User? {
        if (isUserDetailsAvailable()) {
            return db.collection(DBConstants.Collection.users).document(getCurrentUserId())
                .get().result.toObject<User>()
        }
        return null
    }
}