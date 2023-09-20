package com.pradyotprkshpokedex.core.controller

import com.pradyotprkshpokedex.domain.modal.Pagination
import com.pradyotprkshpokedex.domain.service.DefaultServiceImplementation
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DefaultController(val defaultService: DefaultServiceImplementation) {
    suspend inline fun <reified T> respondWithDetails(data: Pagination): List<T> {
        return coroutineScope {
            val count = data.results.size
            val channels = Channel<T>()
            data.results.forEach { result ->
                result.url?.let { url ->
                    launch {
                        delay(1)
                        channels.send(
                            defaultService.makeGetRequest<T>(path = url)
                        )
                    }
                }
            }
            val details = mutableListOf<T>()
            repeat(count) {
                details.add(channels.receive())
            }

            details
        }
    }
}