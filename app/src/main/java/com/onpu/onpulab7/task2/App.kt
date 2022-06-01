package com.onpu.onpulab7.task2

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.util.Log
import androidx.core.content.ContextCompat

class App : Application() {

    private val batteryReceiver = BatteryLowReceiver()
    private val cameraReceiver = CameraReceiver()
    private val flyModeReceiver = FlyModeReceiver()


    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()

        val batteryFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(Intent.ACTION_BATTERY_LOW)
        }
        registerReceiver(batteryReceiver, batteryFilter)

        val cameraFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(Intent.ACTION_CAMERA_BUTTON)
        }
        registerReceiver(cameraReceiver, cameraFilter)

        val flyModeFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        }
        registerReceiver(flyModeReceiver, flyModeFilter)
    }

    override fun onTerminate() {
        super.onTerminate()

        unregisterReceiver(batteryReceiver)
        unregisterReceiver(cameraReceiver)
        unregisterReceiver(flyModeReceiver)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "MESSAGES",
                "Messages",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "Incoming messages from broadcast receivers"

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}