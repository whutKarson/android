package com.example.myfirstapplication

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.NotificationCompat

class DisplayMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)

        // Get the Intent that started this activity and extract the string
        val message = intent.getStringExtra(EXTRA_MESSAGE)

        // Capture the layout's TextView and set the string as its text
        val textView = findViewById<TextView>(R.id.textView).apply {
            text = message
        }
        createNotification()


    }

    private fun createNotification(){

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val intent = Intent()
        var notification: Notification? = null
        intent.setClass(this@DisplayMessageActivity, DisplayMessageActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0,intent,0)

        //OS later than 8.0
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O){
            val channelId = "notification_simple"
            val notificationChannel = NotificationChannel(channelId,"simple",NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(notificationChannel)

            notification = Notification.Builder(this)
                .setChannelId(channelId)
                .setContentTitle("hello notification")
                .setContentText("this is testing for notification from os 8.0")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.notification_icon_background)
                .setWhen(System.currentTimeMillis())
                .setTicker("i am ticker text")
                .setDefaults(Notification.DEFAULT_SOUND)
                .build()
        }else {
            val notificationBuilder = NotificationCompat.Builder(this)
                .setContentTitle("hello notification")
                .setContentText("this is testing for notification from less than os 8.0")
                .setSmallIcon(R.drawable.notification_icon_background)
                .setOngoing(true)
                .setWhen(System.currentTimeMillis())
//.setChannel(id)//无效
            notification = notificationBuilder.build()
        }
        notificationManager.notify(1, notification)
    }


}
