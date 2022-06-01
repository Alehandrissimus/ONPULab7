package com.onpu.onpulab7.task1

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.onpu.onpulab7.R

class MainActivity : AppCompatActivity() {

    private val batteryReceiver = BatteryLowReceiver { message ->
        val dialogFragment = AlertDialog(message)
        dialogFragment.show(supportFragmentManager, "tag")
    }
    private val cameraReceiver = CameraReceiver { message ->
        val dialogFragment = AlertDialog(message)
        dialogFragment.show(supportFragmentManager, "tag")
    }
    private val flyModeReceiver = FlyModeReceiver { message ->
        val dialogFragment = AlertDialog(message)
        dialogFragment.show(supportFragmentManager, "tag")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    override fun onStop() {
        super.onStop()

        unregisterReceiver(batteryReceiver)
        unregisterReceiver(cameraReceiver)
        unregisterReceiver(flyModeReceiver)
    }
}
