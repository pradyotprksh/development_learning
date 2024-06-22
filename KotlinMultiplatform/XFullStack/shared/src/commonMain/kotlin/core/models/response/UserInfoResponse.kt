package core.models.response

import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponse(
    val id: String,
    val name: String,
    val username: String,
    val bio: String?,
    val emailAddress: String?,
    val phoneNumber: String?,
    val profilePicture: String?,
    val dateOfBirth: Long,
    val following: Int,
    val followers: Int,
    val isFollowedByCurrentUser: Boolean,
    val isFollowingCurrentUser: Boolean,
    val isSameUser: Boolean,
    val chatId: String?,
)
