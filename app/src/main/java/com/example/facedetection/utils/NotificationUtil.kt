package com.example.facedetection.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import com.example.facedetection.R

/**
 * https://developer.android.com/training/notify-user/build-notification
 * */
class NotificationUtil {

    companion object {
        fun createNotificationChannel(context: Context) {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = context.resources.getString(R.string.app_name)
                val descriptionText = context.resources.getString(R.string.app_name)
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(Constants.NOTIFICATION_CHANEL, name, importance).apply {
                    description = descriptionText
                }
                // Register the channel with the system
                val notificationManager: NotificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
        }

        fun sendSimpleNotification(context: Context, count: Int) {
            val builder = NotificationCompat.Builder(context, Constants.NOTIFICATION_CHANEL)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Face detected count :: $count")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            with(NotificationManagerCompat.from(context)) {
                // notificationId is a unique int for each notification that you must define
                notify(Constants.STATIC_NOTIFICATION_ID, builder.build())
            }
        }
    }
}