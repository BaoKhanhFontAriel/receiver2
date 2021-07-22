package com.example.receiver2;

import android.util.Log;

public class MessageEntry {
    private String senderMessage;
    private String receiverMessage;

    public MessageEntry(String sender, String receiver) {
        this.receiverMessage = receiver;
        this.senderMessage = sender;
    }

    public String getSenderMessage() {

        if (senderMessage != null) {
            return senderMessage;
        }
        return "";
    }

    public String getReceiverMessage() {

        if (receiverMessage != null) {
            return receiverMessage;
        }
        return "";
    }

}
