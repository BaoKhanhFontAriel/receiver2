package com.example.receiver2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

            Log.d(MainActivity.TAG, "onReceive: ");
            Intent i = new Intent("update message");
            String message = intent.getStringExtra("sender message");
            i.putExtra("sender message", message);

            context.sendBroadcast(i);
    }
}