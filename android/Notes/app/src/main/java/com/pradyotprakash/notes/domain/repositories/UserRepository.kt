package com.pradyotprakash.notes.domain.repositories

import com.pradyotprakash.notes.device.dao.UserDao
import com.pradyotprakash.notes.device.entity.User

class UserRepository(
    private val userDao: UserDao,
) {
    fun getAllUser() = userDao.getUsers()

    fun getUsersByUsername(username: String) =
        userDao.getUsersByUsername(username = username)

    fun getUsersByEmailId(emailId: String) =
        userDao.getUsersByEmailId(emailId = emailId)

    fun createUser(user: User) {
        userDao.insert(user = user)
    }
}