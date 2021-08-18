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