package com.pradyotprakash.xfullstack.features.authentication.controllers.register

import com.pradyotprakash.xfullstack.core.security.hashing.HashingService
import com.pradyotprakash.xfullstack.data.request.RegisterRequest
import com.pradyotprakash.xfullstack.data.user.User
import com.pradyotprakash.xfullstack.data.user.UserDataSource
import com.pradyotprakash.xfullstack.features.authentication.resource.AuthenticationResource
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond

class RegisterControllerImplementation : RegisterController {
    override suspend fun registerUser(
        call: ApplicationCall,
        resource: AuthenticationResource.Register,
        hashingService: HashingService,
        userDataSource: UserDataSource
    ) {
        val registerRequest = call.receive<RegisterRequest>()

        if (!registerRequest.isValid()) {
            call.respond(HttpStatusCode.BadRequest)
            return
        }

        if (!registerRequest.isPasswordValid() || !registerRequest.isUsernameValid()) {
            call.respond(HttpStatusCode.Conflict)
            return
        }

        val saltedHash = hashingService.generateSaltedHash(value = registerRequest.password)

        val user = User(
            username = registerRequest.username,
            password = saltedHash.hash,
            salt = saltedHash.salt,
            bio = registerRequest.bio,
            profilePicture = registerRequest.profilePicture,
            dateOfBirth = registerRequest.dateOfBirth,
            emailAddress = registerRequest.emailAddress,
            phoneNumber = registerRequest.phoneNumber,
        )

        val wasAcknowledged = userDataSource.insertNewUser(user)

        if (!wasAcknowledged) {
            call.respond(HttpStatusCode.Conflict)
            return
        }

        call.respond(HttpStatusCode.OK)
    }
}