package com.example.mynotificationdemo

import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("MainActivity", " started")
        Toast.makeText(this, "MainActivity started", Toast.LENGTH_LONG)
        if(!notificationListenerEnable()){
            gotoNotificationAccessSetting(this)
        }

        val intent = Intent(this, MyService::class.java)
        startService(intent)
    }

    private fun gotoNotificationAccessSetting(context: Context)
            : Boolean {
        try {
            val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            return true
        }catch (e: ActivityNotFoundException){
            try {
                val intent = Intent()
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                val cn: ComponentName = ComponentName("com.android.setting","com.android.settings.Settings\$NotificationAccessSettingsActivity")
                intent.setComponent(cn)
                intent.putExtra(":settings:show_fragment", "NotificationAccessSettings")
                context.startActivity(intent)
                return true
            }catch (ex : Exception){
                ex.printStackTrace()
            }
            return false
        }
        return false
    }

    private fun notificationListenerEnable():Boolean {
        var enable = false
        val packageName = getPackageName()
        val flat = Settings.Secure.getString(contentResolver, "enabled_notification_listeners")
        if (null != flat){
            enable = flat.contains(packageName)
        }
        return enable
    }
}
