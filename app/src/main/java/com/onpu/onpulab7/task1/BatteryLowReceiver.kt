package com.onpu.onpulab7.task1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.onpu.onpulab7.R

class BatteryLowReceiver(val callback: (String) -> Unit) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            intent?.let { actionIntent ->
                if (actionIntent.action == Intent.ACTION_BATTERY_LOW) {
                    callback("Battery is low currently!")
                }
            }
        }
    }
}
