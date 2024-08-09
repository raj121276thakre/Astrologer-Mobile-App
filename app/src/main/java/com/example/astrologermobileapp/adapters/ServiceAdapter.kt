package com.example.astrologermobileapp.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.astrologermobileapp.R
import com.example.astrologermobileapp.models.Service


class ServiceAdapter(private val context: Context, private val serviceList: List<Service>) :
    RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_service, parent, false)
        return ServiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service = serviceList[position]
        holder.txtServiceName.text = service.name
        holder.imgServiceIcon.setImageResource(service.iconResId)
        holder.imgBackground.setImageResource(service.backgroundResId)
    }

    override fun getItemCount(): Int {
        return serviceList.size
    }

    inner class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtServiceName: TextView = itemView.findViewById(R.id.txt_service_name)
        val imgServiceIcon: ImageView = itemView.findViewById(R.id.img_service_icon)
        val imgBackground: ImageView = itemView.findViewById(R.id.img_background)
    }
}
