package com.onpu.onpulab7.task2

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

class FlyModeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            intent?.let { actionIntent ->
                if (actionIntent.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
                    val isAirplaneModeOn = getAirplaneMode(context)
                    if (isAirplaneModeOn) {
                        sendNotification(context, "Airplane mode is enabled!")
                    } else {
                        sendNotification(context, "Airplane mode is disabled!")
                    }
                }
            }
        }
    }

    private fun getAirplaneMode(context: Context?) : Boolean {
        return Settings.Global.getInt(context?.contentResolver,
            Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
    }

    private fun sendNotification(context: Context, message: String) {
        val builder = NotificationCompat.Builder(context, "MESSAGES")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Incoming broadcast!")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        NotificationManagerCompat.from(context).apply {
            this.notify(0,  builder.build())
        }
    }
}