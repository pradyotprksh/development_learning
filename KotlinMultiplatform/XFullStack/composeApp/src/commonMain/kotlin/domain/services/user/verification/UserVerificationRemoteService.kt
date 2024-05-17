package domain.services.user.verification

import data.response.DefaultResponse
import data.response.XFullStackResponse

interface UserVerificationRemoteService {
    suspend fun isUserPresent(value: String): XFullStackResponse<DefaultResponse>
}