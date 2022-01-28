package com.wayfortech.notificationsample

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.TaskStackBuilder
import com.wayfortech.notificationsample.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {

    private lateinit var activityBinding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)

        activityBinding.btnSendMessageNotification.setOnClickListener {
            NotificationUtils.postNotification(
                context = this,
                channelId = NotificationUtils.NOTIFICATION_CHANNEL_ID_MESSAGE,
                channelName = NotificationUtils.NOTIFICATION_CHANNEL_NAME_MESSAGE,
                channelDescription = NotificationUtils.NOTIFICATION_CHANNEL_DESCRIPTION_MESSAGE
            )
        }

        val intent = Intent(this, ThirdActivity::class.java)
        val pendingIntent =
            TaskStackBuilder.create(this).run {
                addNextIntentWithParentStack(intent)
                getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
            }

        activityBinding.btnSendCallNotification.setOnClickListener {
            if (pendingIntent != null) {
                NotificationUtils.postNotificationWithClick(
                    context = this,
                    channelId = NotificationUtils.NOTIFICATION_CHANNEL_ID_CALL,
                    channelName = NotificationUtils.NOTIFICATION_CHANNEL_NAME_CALL,
                    channelDescription = NotificationUtils.NOTIFICATION_CHANNEL_DESCRIPTION_CALL,
                    pendingIntent = pendingIntent
                )
            }
        }
    }
}