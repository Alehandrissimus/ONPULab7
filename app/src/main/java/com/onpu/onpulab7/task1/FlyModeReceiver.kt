package com.onpu.onpulab7.task1

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import com.onpu.onpulab7.R

class FlyModeReceiver(val callback: (String) -> Unit) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            intent?.let { actionIntent ->
                if (actionIntent.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
                    val isAirplaneModeOn = getAirplaneMode(context)
                    if (isAirplaneModeOn) {
                        callback("Airplane mode is enabled!")
                    } else {
                        callback("Airplane mode is disabled!")
                    }
                }
            }
        }
    }

    private fun getAirplaneMode(context: Context?) : Boolean {
        return Settings.Global.getInt(context?.contentResolver,
            Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
    }
}
