package com.example.astrologermobileapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.astrologermobileapp.R
import com.example.astrologermobileapp.models.Astrologer
import com.example.astrologermobileapp.models.AstrologerDetails


class AstrologersDetailsAdapter(
    private val astrologerList: List<AstrologerDetails>,
    private val itemClickListener: (AstrologerDetails) -> Unit
) : RecyclerView.Adapter<AstrologersDetailsAdapter.AstrologerViewHolder>() {

    class AstrologerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        private val descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)
        private val languageTextView: TextView = view.findViewById(R.id.languageTextView)
        private val experienceTextView: TextView = view.findViewById(R.id.experienceTextView)
        private val profileImageView: ImageView = view.findViewById(R.id.imageView)
        private val chatButton: Button = view.findViewById(R.id.chatButton)

        fun bind(astrologer: AstrologerDetails, clickListener: (AstrologerDetails) -> Unit) {
            nameTextView.text = astrologer.name
            descriptionTextView.text = astrologer.description
            languageTextView.text = astrologer.languages
            experienceTextView.text = astrologer.experience

            Glide.with(profileImageView.context)
                .load(astrologer.profileImage)
                .into(profileImageView)

            chatButton.setOnClickListener { clickListener(astrologer) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AstrologerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_astro_chats_layout, parent, false)
        return AstrologerViewHolder(view)
    }

    override fun onBindViewHolder(holder: AstrologerViewHolder, position: Int) {
        holder.bind(astrologerList[position], itemClickListener)
    }

    override fun getItemCount(): Int {
        return astrologerList.size
    }
}
