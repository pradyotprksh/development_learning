package data.remote.user.current

import core.models.request.XFullStackClientRequestDetails
import core.network.NetworkClient
import data.request.LoginRequest
import data.request.RegisterRequest
import data.response.AuthenticationResponse
import data.response.UserInfoResponse
import data.response.XFullStackResponse
import domain.services.user.current.CurrentUserRemoteService
import utils.Constants.Paths.Authentication.AUTH
import utils.Constants.Paths.Authentication.AUTHENTICATE
import utils.Constants.Paths.Authentication.LOGIN
import utils.Constants.Paths.Authentication.REGISTER
import utils.Constants.Paths.Authentication.USER_INFO

class CurrentUserRemoteServiceImplementation(
    private val networkClient: NetworkClient
) : CurrentUserRemoteService {
    override suspend fun authenticateUser(): XFullStackResponse<Nothing> {
        val response = networkClient.get<XFullStackResponse<Nothing>>(
            details = XFullStackClientRequestDetails(
                endpoint = "$AUTH$AUTHENTICATE"
            )
        )

        return response.getOrThrow()
    }

    override suspend fun registerUser(registerRequest: RegisterRequest): XFullStackResponse<Nothing> {
        val response = networkClient.post<XFullStackResponse<Nothing>>(
            details = XFullStackClientRequestDetails(
                endpoint = "$AUTH$REGISTER",
                body = registerRequest,
            )
        )

        return response.getOrThrow()
    }

    override suspend fun loginUser(loginRequest: LoginRequest): XFullStackResponse<AuthenticationResponse> {
        val response = networkClient.post<XFullStackResponse<AuthenticationResponse>>(
            details = XFullStackClientRequestDetails(
                endpoint = "$AUTH$LOGIN",
                body = loginRequest,
            )
        )

        return response.getOrThrow()
    }

    override suspend fun getUserInfo(): XFullStackResponse<UserInfoResponse> {
        val response = networkClient.get<XFullStackResponse<UserInfoResponse>>(
            details = XFullStackClientRequestDetails(
                endpoint = "$AUTH$USER_INFO"
            )
        )

        return response.getOrThrow()
    }
}