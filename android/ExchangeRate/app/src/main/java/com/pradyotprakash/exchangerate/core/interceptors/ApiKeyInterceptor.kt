package com.pradyotprakash.exchangerate.core.interceptors

import com.pradyotprakash.exchangerate.core.request.Constants.API_KEY
import com.pradyotprakash.exchangerate.core.request.Constants.API_KEY_HEADER
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiKeyInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().run {
            addHeader(API_KEY_HEADER, API_KEY)
            build()
        }
        return chain.proceed(request)
    }
}