package com.pradyotprakash.notes.core.services

import android.app.Service
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.IBinder
import android.widget.Toast

class AppStartService: Service() {
    private val binder = LocalBinder()

    override fun onCreate() {
        Toast.makeText(this, "AppStartService created", Toast.LENGTH_SHORT).show()
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "AppStartService started", Toast.LENGTH_SHORT).show()
        return START_STICKY
    }

    override fun onDestroy() {
        Toast.makeText(this, "AppStartService destroyed", Toast.LENGTH_SHORT).show()
    }

    override fun unbindService(conn: ServiceConnection) {
        Toast.makeText(this, "AppStartService unbinded", Toast.LENGTH_SHORT).show()
        super.unbindService(conn)
    }

    override fun onBind(p0: Intent?): IBinder {
        Toast.makeText(this, "AppStartService binded", Toast.LENGTH_SHORT).show()
        return binder
    }

    inner class LocalBinder: Binder() {
        fun getService(): AppStartService = this@AppStartService
    }
}