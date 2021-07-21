package com.example.receiver2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
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

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: " + intent.getAction());
            if (intent.getAction().equals("")) {
                String message = intent.getStringExtra("sender message");
                Log.d("TAG", "onReceive: string message" + message);
                receiverAdapter.insertItem(new MessageEntry(message, ""));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.message_display);
        Button sendButton = findViewById(R.id.sendButton);
        TextView receiverTitle = findViewById(R.id.receiverTitle);
        boxChat = findViewById(R.id.boxchat);



        // Send the message
        Intent receiverIntent = new Intent();
        receiverIntent.setAction("com.example.receiverMessage");

        receiverAdapter = new ReceiverAdapter(Message.getInstance().getMessageList());
        recyclerView.setAdapter(receiverAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        sendButton.setOnClickListener(v -> {
            Message.getInstance().addMessage(new MessageEntry("", boxChat.getText().toString()));
            receiverAdapter.notifyItemInserted(Message.getInstance().getMessageList().size() - 1);
            receiverIntent.putExtra("receiver message", boxChat.getText().toString());
            sendBroadcast(receiverIntent);
            boxChat.getText().clear();
        });

    processIntent(getIntent());

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Log.d(TAG, intent.getAction());
        processIntent(intent);
    }

    void processIntent(Intent intent){}

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");

        IntentFilter intentFilter = new IntentFilter("com.example.myMessage");
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
        unregisterReceiver(broadcastReceiver);

    }
}