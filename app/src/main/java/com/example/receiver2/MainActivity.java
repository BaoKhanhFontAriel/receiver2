package com.example.receiver2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    static String TAG = "ReceiverMain";
    RecyclerView recyclerView;
    EditText boxChat;
    ReceiverAdapter receiverAdapter;
    BroadcastReceiver myReceiver;
    Intent receiverIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.message_display);
        Button sendButton = findViewById(R.id.sendButton);
        TextView receiverTitle = findViewById(R.id.receiverTitle);
        boxChat = findViewById(R.id.boxchat);
        myReceiver = new MyReceiver();

        // Send the message

        IntentFilter intentFilter = new IntentFilter("com.example.myMessage");
        registerReceiver(myReceiver, intentFilter);

        receiverIntent = new Intent("com.example.myMessage");
        receiverIntent.setComponent(new ComponentName("com.example.sender2", "com.example.sender2.MyReceiver"));

        receiverAdapter = new ReceiverAdapter(Message.getInstance().getMessageList());

        recyclerView.setAdapter(receiverAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        sendButton.setOnClickListener(v -> {
            showMessage("", boxChat.getText().toString());
            receiverIntent.putExtra("receiver message", boxChat.getText().toString());
            sendBroadcast(receiverIntent);

//            boxChat.getText().clear();
        });

        processIntent(getIntent());
    }


    public void showMessage(String sender, String receiver) {
        receiverAdapter.insertItem(new MessageEntry(sender, receiver));
        recyclerView.scrollToPosition(Message.getInstance().getMessageList().size() - 1);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        processIntent(intent);
    }

    void processIntent(Intent intent) {
        String message = intent.getStringExtra("sender message");
        showMessage(message, "");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }

}