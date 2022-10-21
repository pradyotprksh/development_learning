package com.project.pradyotprakash.rental.domain.modal

data class SearchEntity(
    val properties: SearchPropertyEntity,
    val users: SearchUserEntity,
)

data class SearchPropertyEntity(
    val count: Int = 0,
    val details: List<PropertyEntity> = emptyList()
)

data class SearchUserEntity(
    val count: Int = 0,
    val details: List<UserEntity> = emptyList()
)
