/*
 * MIT License
 *
 * Copyright (c) 2021 Pradyot Prakash
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
*/
package com.project.pradyotprakash.whatsappcompose.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.project.pradyotprakash.whatsappcompose.models.Status
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

    /**
     * Get current user details as a listener
     */
    fun userDetailsListener(callbacks: FirestoreCallbacks) {
        db.collection(DBConstants.Collection.users).document(getCurrentUserId())
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    callbacks.onError(e.localizedMessage ?: "")
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val data = snapshot.toObject<User>()
                    if (data != null) {
                        callbacks.userDetails(user = data)
                    } else {
                        callbacks.onError("")
                    }
                } else {
                    callbacks.onError("")
                }
            }
    }

    /**
     * Create a status by the current user
     */
    fun createStatus(status: Status, callbacks: FirestoreCallbacks) {
        db.collection(DBConstants.Collection.status).document().set(status)
            .addOnCompleteListener { task ->
                if (task.exception != null) {
                    callbacks.onError(task.exception?.localizedMessage ?: "")
                    return@addOnCompleteListener
                }
                callbacks.isTrue()
            }
    }

    /**
     * Get current user reference
     */
    fun currentUserReference(): DocumentReference {
        return db.collection(DBConstants.Collection.users).document(getCurrentUserId())
    }

    /**
     * Create a listener for status in the application
     */
    fun allStatus(callbacks: FirestoreCallbacks) {
        db.collection(DBConstants.Collection.status)
            .orderBy(DBConstants.DocumentField.createdBy, Query.Direction.DESCENDING)
            .addSnapshotListener { value, e ->
                if (e != null) {
                    callbacks.onError(e.localizedMessage ?: "")
                    return@addSnapshotListener
                }

                val status = ArrayList<Status>()
                if (value != null) {
                    for (doc in value) {
                        status.add(doc.toObject())
                    }

                    callbacks.status(newStatus = status)
                } else {
                    callbacks.onError("")
                }
            }
    }

    /**
     * Get all user lists
     */
    fun allUsers(callbacks: FirestoreCallbacks) {
        db.collection(DBConstants.Collection.users).orderBy(DBConstants.DocumentField.name)
            .addSnapshotListener { value, e ->
                if (e != null) {
                    callbacks.onError(e.localizedMessage ?: "")
                    return@addSnapshotListener
                }

                val users = ArrayList<User>()
                if (value != null) {
                    for (doc in value) {
                        users.add(doc.toObject())
                    }

                    callbacks.userList(users = users)
                } else {
                    callbacks.onError("")
                }
            }
    }
}