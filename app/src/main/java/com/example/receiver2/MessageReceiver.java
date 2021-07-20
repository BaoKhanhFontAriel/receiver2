package com.example.receiver2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MessageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("")){
            String message = intent.getStringExtra("message");
            Log.d("TAG", "onReceive: string message" + message);
            Message.getInstance().addMessage(new MessageEntry(message, ""));
        }
    }
}