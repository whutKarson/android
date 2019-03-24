package com.example.mynotificationdemo

import android.app.Notification
import android.app.Service
import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import java.lang.Exception
import java.text.SimpleDateFormat

class MyService : NotificationListenerService() {

    var dateformat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")


    override fun onCreate() {
        super.onCreate()
        Log.i("NotificationListenerService ", "service start!!")
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        if(null != sbn){
            val notification = sbn.notification
            val tickerText = notification.tickerText.toString()
            val packageName = sbn.packageName
            val postTime = sbn.postTime
            val settingText = notification.settingsText


            if (Build.VERSION.SDK_INT >Build.VERSION_CODES.JELLY_BEAN_MR2) {
                val extras = notification.extras
                val notificationTitle = extras.getString(Notification.EXTRA_TITLE)
                val notificationText = extras.getCharSequence(Notification.EXTRA_TEXT)
                val notificationSubText = extras.getCharSequence (Notification.EXTRA_SUB_TEXT)

                Log.i("NotificationListenerService","packageName: "+ packageName
                        + ",settingText: " + settingText
                        + ",tickerText: " + tickerText
                        + ",notificationTitle: " + notificationTitle
                        + ", notifcationText:  " + notificationText
                        + ", notificationSubText: " + notificationSubText
                        + " at time: " + dateformat.format(postTime))
            }else{
                Log.i("NotificationListenerService","packageName: "+ packageName
                        + ",settingText: " + settingText
                        + ",tickerText: " + tickerText
                        + " at time: " + postTime)
            }

        }


    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
        Log.i("NotificationListenerService","Remove notification:")
    }



}
