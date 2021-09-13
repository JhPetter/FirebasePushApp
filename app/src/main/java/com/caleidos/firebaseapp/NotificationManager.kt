package com.caleidos.firebaseapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.ImageView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.google.firebase.messaging.RemoteMessage

fun ImageView.load(url: String) {
    if (url.isNotEmpty()) {
        val imgUri = url.toUri().buildUpon().scheme("https").build()
        Glide.with(this.context)
            .asBitmap()
            .load(imgUri)
            .into(this)
    }
}

fun showNotification(context: Context, notification: RemoteMessage) {
    // val url = URL(notification.data["pinpoint.url"].toString())
    // val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
    val builder = NotificationCompat.Builder(
        context,
        context.getString(R.string.default_notification_channel_id)
    )
        .setSmallIcon(R.drawable.caleidos)
        .setContentTitle(notification.data["pinpoint.notification.title"])
        .setContentText(notification.data["pinpoint.notification.body"])
        // .setLargeIcon(image)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channelId = context.getString(R.string.default_notification_channel_id)
        val channel = NotificationChannel(
            channelId,
            context.getString(R.string.default_notification_channel_Name),
            NotificationManager.IMPORTANCE_HIGH
        )
        NotificationManagerCompat.from(context).createNotificationChannel(channel)
        builder.setChannelId(channelId)
    }
    val pendingIntent =
        PendingIntent.getActivity(context, 100, Intent(context, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            putExtra("title", notification.data["pinpoint.notification.title"])
            putExtra("body", notification.data["pinpoint.notification.body"])
            putExtra("image", notification.data["pinpoint.url"].toString())

        }, PendingIntent.FLAG_UPDATE_CURRENT)
    builder.setContentIntent(pendingIntent)

    with(NotificationManagerCompat.from(context)) {
        notify(1, builder.build())
    }
}