package com.onpu.onpulab7.task1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.onpu.onpulab7.R

class CameraReceiver(private val callback: (String) -> Unit) : BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            intent?.let { actionIntent ->
                if (actionIntent.action == Intent.ACTION_CAMERA_BUTTON) {
                    callback("Camera button clicked!")
                }
            }
        }
    }
}
