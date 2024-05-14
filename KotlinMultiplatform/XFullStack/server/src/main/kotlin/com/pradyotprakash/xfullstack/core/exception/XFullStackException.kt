package com.pradyotprakash.xfullstack.core.exception

sealed class XFullStackException : Throwable()

class InvalidParameter(message: String) : XFullStackException()