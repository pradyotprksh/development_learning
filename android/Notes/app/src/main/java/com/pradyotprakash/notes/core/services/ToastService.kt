package com.pradyotprakash.notes.core.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class ToastService : Service() {
    override fun onCreate() {
        Toast.makeText(this, "ToastService created", Toast.LENGTH_SHORT).show()
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "ToastService started", Toast.LENGTH_SHORT).show()
        return START_STICKY
    }

    override fun onDestroy() {
        Toast.makeText(this, "ToastService destroyed", Toast.LENGTH_SHORT).show()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}