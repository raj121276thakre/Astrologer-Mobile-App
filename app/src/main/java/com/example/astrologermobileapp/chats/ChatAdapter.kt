package com.example.astrologermobileapp.chats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.astrologermobileapp.R


class ChatAdapter(private val chatList: List<ChatMessage>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_SENT = 1
    private val VIEW_TYPE_RECEIVED = 2

    override fun getItemViewType(position: Int): Int {
        return if (chatList[position].isSentByUser) VIEW_TYPE_SENT else VIEW_TYPE_RECEIVED
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_SENT) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_sent, parent, false)
            SentMessageViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_received, parent, false)
            ReceivedMessageViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chatMessage = chatList[position]
        if (holder is SentMessageViewHolder) {
            holder.bind(chatMessage)
        } else if (holder is ReceivedMessageViewHolder) {
            holder.bind(chatMessage)
        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    class SentMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textMessage: TextView = itemView.findViewById(R.id.text_message)
        private val textTimestamp: TextView = itemView.findViewById(R.id.text_timestamp)

        fun bind(chatMessage: ChatMessage) {
            textMessage.text = chatMessage.message
            textTimestamp.text = chatMessage.timestamp
        }
    }

    class ReceivedMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textMessage: TextView = itemView.findViewById(R.id.text_message)
        private val textTimestamp: TextView = itemView.findViewById(R.id.text_timestamp)

        fun bind(chatMessage: ChatMessage) {
            textMessage.text = chatMessage.message
            textTimestamp.text = chatMessage.timestamp
        }
    }
}
