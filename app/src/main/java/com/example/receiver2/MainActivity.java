package com.example.receiver2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    EditText boxChat;
    MessageReceiver messageReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.message_display);
        Button sendButton = findViewById(R.id.sendButton);
        TextView receiverTitle = findViewById(R.id.receiverTitle);
        boxChat = findViewById(R.id.boxchat);


        sendButton.setOnClickListener(v -> {
            Message.getInstance().addMessage(new MessageEntry(boxChat.getText().toString(), ""));
            boxChat.getText().clear();
        });

        ReceiverAdapter receiverAdapter = new ReceiverAdapter(Message.getInstance().getMessageList());
        recyclerView.setAdapter(receiverAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        messageReceiver = new MessageReceiver();
        IntentFilter intentFilter = new IntentFilter("com.example.myMessage");
        registerReceiver(messageReceiver, intentFilter);

        // Send the message
        Intent receiverIntent = new Intent();
        receiverIntent.setAction("com.example.myMessage");

        receiverIntent.putExtra("message", boxChat.getText().toString());


        Message.getInstance().addMessage(new MessageEntry("", boxChat.getText().toString()));

        sendBroadcast(receiverIntent);

    }
}