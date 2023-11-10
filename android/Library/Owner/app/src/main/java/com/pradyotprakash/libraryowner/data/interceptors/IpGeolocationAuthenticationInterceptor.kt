package com.pradyotprakash.libraryowner.data.interceptors

import com.pradyotprakash.libraryowner.core.utils.Constants
import com.pradyotprakash.libraryowner.core.utils.confidential.IpGeolocation
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class IpGeolocationAuthenticationInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url

        val url = originalHttpUrl.newBuilder().run {
            addQueryParameter(Constants.IP_GEOLOCATION_API_KEY, IpGeolocation.API_KEY)
            build()
        }

        val request = originalRequest.newBuilder().run {
            url(url)
            build()
        }

        return chain.proceed(request)
    }
}