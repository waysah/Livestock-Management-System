package com.example.livestockmanagement;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

    public class ReminderReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String feedType = intent.getStringExtra("FEED_TYPE");
            Toast.makeText(context, "Time to feed: " + feedType, Toast.LENGTH_LONG).show();

        }
    }

