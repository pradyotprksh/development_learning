package com.pradyotprakash.libraryowner.device.network

import com.orhanobut.logger.Logger
import java.io.IOException
import java.net.InetSocketAddress
import javax.net.SocketFactory

object DoesNetworkHaveInternet {
    private val TAG = this.javaClass.name

    fun execute(socketFactory: SocketFactory): Boolean {
        return try {
            val socket = socketFactory.createSocket() ?: throw IOException("Socket is null.")
            socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
            socket.close()
            Logger.d(TAG, "PING success.")
            true
        } catch (e: IOException) {
            Logger.e(TAG, "No internet connection.")
            false
        }
    }
}
