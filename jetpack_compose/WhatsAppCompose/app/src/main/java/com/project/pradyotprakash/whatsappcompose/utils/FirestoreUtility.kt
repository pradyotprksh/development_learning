package com.project.pradyotprakash.whatsappcompose.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.SetOptions
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
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    /**
     * Check for user details
     */
    fun checksForUserDetails(callbacks: FirestoreCallbacks) {
        if (firebaseAuth.currentUser != null) {
            val userId = getCurrentUserId()
            db.collection(DBConstants.Collection.users).document(userId).get()
                .addOnCompleteListener { task ->
                    if (task.exception != null) {
                        callbacks.onError(task.exception?.localizedMessage ?: "")
                        return@addOnCompleteListener
                    }
                    if (task.result.exists()) {
                        val userDetails = task.result.toObject<User>()
                        if (userDetails != null) {
                            callbacks.userDetails(user = userDetails)
                        } else {
                            callbacks.isFalse()
                        }
                    } else {
                        callbacks.isFalse()
                    }
                }
        }
    }

    /**
     * Set current user details
     */
    fun setUserDetails(data: User, callbacks: FirestoreCallbacks) {
        db.collection(DBConstants.Collection.users).document(getCurrentUserId())
            .set(data, SetOptions.merge()).addOnCompleteListener { task ->
                if (task.exception != null) {
                    callbacks.onError(task.exception?.localizedMessage ?: "")
                    return@addOnCompleteListener
                }
                callbacks.isTrue()
            }
    }

    /**
     * Get the current user id from firebase
     */
    private fun getCurrentUserId(): String {
        return firebaseAuth.currentUser!!.uid
    }
}