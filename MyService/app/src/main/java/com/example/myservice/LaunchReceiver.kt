package com.example.myservice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.myservice.MyService

class LaunchReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        TODO("LaunchReceiver.onReceive() is not implemented")
        val intent = Intent( context ,MyService::class.java)
        context.startService(intent)
    }
}
