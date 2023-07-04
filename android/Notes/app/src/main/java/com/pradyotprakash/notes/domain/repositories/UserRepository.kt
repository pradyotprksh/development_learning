package com.pradyotprakash.notes.domain.repositories

import com.pradyotprakash.notes.device.dao.UserDao

class UserRepository(
    private val userDao: UserDao,
) {
    suspend fun getAllUser() = userDao.getUsers()
}