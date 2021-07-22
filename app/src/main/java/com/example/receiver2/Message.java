package com.example.receiver2;

import android.util.Log;

import java.util.ArrayList;

public class Message {

    private static Message instance;
    private ArrayList<MessageEntry> messageList = new ArrayList<>();

    public static Message getInstance() {
        if (instance == null){
            instance = new Message();
        }
        return instance;
    }

    public void addMessage(MessageEntry message){
        messageList.add(message);
    }

    public ArrayList<MessageEntry> getMessageList() {

        return messageList;
    }
}
