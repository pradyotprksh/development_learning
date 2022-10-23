package com.project.pradyotprakash.rental.core.interceptor

import com.project.pradyotprakash.rental.core.response.RenterResponse
import com.project.pradyotprakash.rental.core.services.AppCheckService
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
    private val appCheckService: AppCheckService,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().run {
            runBlocking {
                appCheckService.getAppCheckToken().collect {
                    when (it) {
                        is RenterResponse.Success -> addHeader("X-Firebase-AppCheck", it.data)
                        else -> {}
                    }
                }
            }
            build()
        }
        return chain.proceed(request)
    }
}