package com.example.astrologermobileapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.astrologermobileapp.R
import com.example.astrologermobileapp.models.Astrologer
import com.example.astrologermobileapp.models.Feature
import de.hdodenhof.circleimageview.CircleImageView

class AstrologerAdapter(
    private val astrologers: List<Astrologer>,
    private val onAstroClick: (Astrologer) -> Unit
) :
    RecyclerView.Adapter<AstrologerAdapter.AstrologerViewHolder>() {

    class AstrologerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileImage: CircleImageView = itemView.findViewById(R.id.iv_astrologer_profile)
        val name: TextView = itemView.findViewById(R.id.tv_astrologer_name)
        val expertise: TextView = itemView.findViewById(R.id.tv_astrologer_expertise)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AstrologerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_astrologer, parent, false)
        return AstrologerViewHolder(view)
    }

    override fun onBindViewHolder(holder: AstrologerViewHolder, position: Int) {
        val astrologer = astrologers[position]
        holder.name.text = astrologer.name
        holder.expertise.text = astrologer.expertise

        Glide.with(holder.profileImage.context)
            .load(astrologer.profileImageUrl)
            .into(holder.profileImage)

        holder.itemView.setOnClickListener {
            onAstroClick(astrologer) //
        }
    }

    override fun getItemCount(): Int {
        return astrologers.size
    }
}
