package com.pradyotprakash.libraryowner.data.services.firestore

import com.google.firebase.firestore.FirebaseFirestore
import com.pradyotprakash.libraryowner.core.models.User
import com.pradyotprakash.libraryowner.data.services.firestore.utils.Keys
import kotlinx.coroutines.tasks.await

class UserFirestoreServiceImplementation(
    private val firestore: FirebaseFirestore,
) : UserFirestoreService {
    override suspend fun getUserDetails(userId: String) =
        firestore.collection(Keys.Collections.USERS).document(userId).get().await()
            .toObject(User::class.java)

    override suspend fun setUserDetails(user: User) {
        firestore.collection(Keys.Collections.USERS).document(user.userId).set(user).await()
    }
}