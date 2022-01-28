package com.wayfortech.notificationsample

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

object NotificationUtils {

    private const val NOTIFICATION_ID = 1234
    const val NOTIFICATION_CHANNEL_ID_MESSAGE = "Message"
    const val NOTIFICATION_CHANNEL_NAME_MESSAGE = "Message channel"
    const val NOTIFICATION_CHANNEL_DESCRIPTION_MESSAGE =
        "This is message related notification channel"
    const val NOTIFICATION_CHANNEL_ID_CALL = "Call"
    const val NOTIFICATION_CHANNEL_NAME_CALL = "Call channel"
    const val NOTIFICATION_CHANNEL_DESCRIPTION_CALL =
        "This is call related notification channel"

    private fun createNotificationChannel(
        context: Context,
        channelId: String,
        channelName: String,
        channelDescription: String
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val notificationChannelCompat = NotificationChannelCompat.Builder(
                channelId,
                NotificationManagerCompat.IMPORTANCE_HIGH
            )
                .setName(channelName)
                .setDescription(channelDescription).build()

            NotificationManagerCompat.from(context)
                .createNotificationChannel(notificationChannelCompat)
        }
    }

    private fun getNotification(
        context: Context,
        notificationIcon: Int,
        notificationChannelId: String,
        notificationTitle: String,
        notificationText: String,
        pendingIntent: PendingIntent? = null
    ): Notification {

        val notification =
            NotificationCompat.Builder(context, notificationChannelId)
                .setSmallIcon(notificationIcon)
                .setContentTitle(notificationTitle)
                .setContentText(notificationText)

        if (pendingIntent != null) {
            notification.setContentIntent(pendingIntent)
        }

        return notification.build()
    }

    fun postNotification(
        context: Context,
        channelId: String,
        channelName: String,
        channelDescription: String
    ) {

        createNotificationChannel(
            context = context,
            channelId = channelId,
            channelName = channelName,
            channelDescription = channelDescription
        )

        val notification = getNotification(
            context = context,
            notificationIcon = R.mipmap.ic_launcher,
            notificationChannelId = channelId,
            notificationTitle = "Title",
            notificationText = "Text"
        )

        NotificationManagerCompat.from(context)
            .notify(NOTIFICATION_ID, notification)
    }


    fun postNotificationWithClick(
        context: Context,
        channelId: String,
        channelName: String,
        channelDescription: String,
        pendingIntent: PendingIntent
    ) {

        createNotificationChannel(
            context = context,
            channelId = channelId,
            channelName = channelName,
            channelDescription = channelDescription
        )

        val notification = getNotification(
            context = context,
            notificationIcon = R.mipmap.ic_launcher,
            notificationChannelId = channelId,
            notificationTitle = "Title",
            notificationText = "Text",
            pendingIntent = pendingIntent
        )

        NotificationManagerCompat.from(context)
            .notify(NOTIFICATION_ID, notification)
    }

}