package com.onpu.onpulab7.task2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.onpu.onpulab7.R

class BatteryLowReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            intent?.let { actionIntent ->
                if (actionIntent.action == Intent.ACTION_BATTERY_LOW) {
                    sendNotification(context, "Battery is low currently!")
                }
            }
        }
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