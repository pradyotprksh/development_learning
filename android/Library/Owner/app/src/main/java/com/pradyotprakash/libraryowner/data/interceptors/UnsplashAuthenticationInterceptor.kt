package com.pradyotprakash.libraryowner.data.interceptors

import com.pradyotprakash.libraryowner.core.utils.Constants.UNSPLASH_CLIENT_ID
import com.pradyotprakash.libraryowner.core.utils.confidential.Unsplash.ACCESS_KEY
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class UnsplashAuthenticationInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().run {
            addHeader(UNSPLASH_CLIENT_ID, ACCESS_KEY)
            build()
        }
        return chain.proceed(request)
    }
}