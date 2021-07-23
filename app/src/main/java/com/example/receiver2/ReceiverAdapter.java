package com.example.receiver2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReceiverAdapter extends RecyclerView.Adapter<ReceiverAdapter.ReceiverViewHolder> {

    ArrayList<MessageEntry> messagesList = new ArrayList<>();

    public ReceiverAdapter(ArrayList<MessageEntry> messagesList) {
        this.messagesList = messagesList;
    }

    @Override
    public ReceiverViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View senderView = inflater.inflate(R.layout.message_ui, parent, false);

        // Return a new holder instance
        return new ReceiverViewHolder(senderView);
    }

    @Override
    public void onBindViewHolder(ReceiverViewHolder holder, int position) {


        MessageEntry messageEntry = messagesList.get(position);

        Log.d(MainActivity.TAG, "onBindViewHolder: sender: " + messageEntry.getSenderMessage());
        Log.d(MainActivity.TAG, "onBindViewHolder: receiver: " + messageEntry.getReceiverMessage());

        if (!messageEntry.getSenderMessage().isEmpty() && messageEntry.getReceiverMessage().isEmpty()) {
            holder.senderMessage.setText(messageEntry.getSenderMessage());
            holder.receiverMessage.setBackground(null);
        } else if (messageEntry.getSenderMessage().isEmpty() && !messageEntry.getReceiverMessage().isEmpty()) {
            holder.senderMessage.setBackground(null);
            holder.receiverMessage.setText(messageEntry.getReceiverMessage());
        } else {
            holder.senderMessage.setVisibility(View.GONE);
            holder.receiverMessage.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ReceiverViewHolder extends RecyclerView.ViewHolder {
        TextView senderMessage;
        TextView receiverMessage;

        public ReceiverViewHolder(View itemView) {
            super(itemView);

            senderMessage = itemView.findViewById(R.id.send_message);
            receiverMessage = itemView.findViewById(R.id.receive_message);
        }

    }

    public void insertItem(MessageEntry message) {
        this.messagesList.add(message);
        notifyItemInserted(messagesList.size());
    }
}
