package com.example.receiver2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MessageReceiver extends BroadcastReceiver {

    ReceiverAdapter receiverAdapter = new ReceiverAdapter(Message.getInstance().getMessageList());
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MessageReceiver", "onReceive: string message" + intent.getAction());
        if(intent.getAction().equals("")){
            String message = intent.getStringExtra("sender message");

            receiverAdapter.insertItem(new MessageEntry(message, ""));
        }
    }
}