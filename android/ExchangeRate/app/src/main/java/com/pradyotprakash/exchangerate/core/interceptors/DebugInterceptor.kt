package com.pradyotprakash.exchangerate.core.interceptors

import android.content.Context
import com.orhanobut.logger.Logger
import com.pradyotprakash.exchangerate.app.utils.Assets
import com.pradyotprakash.exchangerate.core.utils.Constants.USE_LOCAL_RESPONSE
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject

class DebugInterceptor(
    private val context: Context,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val response: Response = if (USE_LOCAL_RESPONSE) {
            runBlocking {
                delay(2500)
                val path = request.url.encodedPath
                try {
                    val responseString = if (path.contains("/list")) {
                        getResponse("list", context)
                    } else if (path.contains("/live")) {
                        getResponse("live", context)
                    } else {
                        throw ExceptionInInitializerError()
                    }

                    Response.Builder()
                        .code(200)
                        .message(responseString)
                        .request(chain.request())
                        .protocol(Protocol.HTTP_1_1)
                        .body(responseString.toResponseBody("application/json".toMediaTypeOrNull()))
                        .addHeader("content-type", "application/json")
                        .build()
                } catch (e: Exception) {
                    Logger.e(e.toString())
                    chain.proceed(request)
                }
            }
        } else {
            chain.proceed(request)
        }

        return response
    }

    private fun getResponse(fileName: String, context: Context): String {
        try {
            val inputSystem = context.assets.open(Assets.Response(fileName = fileName).path)
            val buffer = ByteArray(inputSystem.available())
            inputSystem.read(buffer)
            inputSystem.close()
            val json = String(buffer, Charsets.UTF_8)
            return JSONObject(json).toString()
        } catch (e: Exception) {
            throw e
        }
    }
}