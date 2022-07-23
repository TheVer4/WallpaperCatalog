package com.example.wallpapercatalog.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class BackgroundService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Service started")
    }

    override fun onBind(p0: Intent?): IBinder? = null


    companion object {
        private const val TAG = "BG-SERVICE"
    }
}