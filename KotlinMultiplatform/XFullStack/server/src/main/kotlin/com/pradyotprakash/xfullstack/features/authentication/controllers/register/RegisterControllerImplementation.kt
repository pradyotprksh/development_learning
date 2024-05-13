package com.pradyotprakash.xfullstack.features.authentication.controllers.register

import com.pradyotprakash.xfullstack.core.security.hashing.HashingService
import com.pradyotprakash.xfullstack.data.user.User
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.authentication.resource.AuthenticationResource
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class RegisterControllerImplementation : RegisterController {
    override suspend fun registerUser(
        call: ApplicationCall,
        resource: AuthenticationResource.Register,
        hashingService: HashingService,
        userDataSource: UserDataSource
    ) {
        if (!resource.isValid()) {
            call.respond(HttpStatusCode.BadRequest)
            return
        }

        if (!resource.isPasswordValid() || !resource.isUsernameValid()) {
            call.respond(HttpStatusCode.Conflict)
            return
        }

        val saltedHash = hashingService.generateSaltedHash(value = resource.password)

        val user = User(
            username = resource.username,
            password = saltedHash.hash,
            salt = saltedHash.salt,
            bio = resource.bio,
            profilePicture = resource.profilePicture,
            dateOfBirth = resource.dateOfBirth,
            emailAddress = resource.emailAddress,
            phoneNumber = resource.phoneNumber,
        )

        val wasAcknowledged = userDataSource.insertNewUser(user)

        if (!wasAcknowledged) {
            call.respond(HttpStatusCode.Conflict)
            return
        }

        call.respond(HttpStatusCode.OK)
    }
}