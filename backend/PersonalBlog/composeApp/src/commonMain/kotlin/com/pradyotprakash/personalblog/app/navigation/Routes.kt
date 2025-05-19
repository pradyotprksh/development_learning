package com.pradyotprakash.personalblog.app.navigation

import kotlinx.serialization.Serializable

@Serializable
object Splash

@Serializable
object AuthenticationOption

@Serializable
data class Home(val admin: Boolean = false)

@Serializable
data object BlogNew

@Serializable
data class BlogDetails(val blogId: String)

@Serializable
data class BlogUpdate(val blogId: String)
