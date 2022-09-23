package com.project.pradyotprakash.rental.data

import com.google.firebase.appcheck.FirebaseAppCheck
import com.project.pradyotprakash.rental.domain.services.AppCheckService

class AppCheckRepository(
    private val firebaseAppCheck: FirebaseAppCheck,
) : AppCheckService {
    override fun getAppCheckToken(onSuccess: (String) -> Unit, onFailure: (Exception) -> Unit) {
        firebaseAppCheck.getAppCheckToken(false)
            .addOnSuccessListener { tokenResponse ->
                val appCheckToken = tokenResponse.token
                onSuccess(appCheckToken)
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }
}