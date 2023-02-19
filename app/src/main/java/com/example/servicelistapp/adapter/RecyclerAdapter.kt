package com.example.servicelistapp.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.servicelistapp.R
import com.example.servicelistapp.datareciever.Service


class RecyclerAdapter(private val services: List<Service>, private val listener: Listener) :
    RecyclerView.Adapter<RecyclerAdapter.serviceViewHolder>() {
    class serviceViewHolder(itemView: View, listener: Listener) :
        RecyclerView.ViewHolder(itemView) {
        val nameTV: TextView = itemView.findViewById(R.id.serviceName)
        val iconIV: ImageView = itemView.findViewById(R.id.serviceIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): serviceViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.service_card, parent, false)
        return serviceViewHolder(itemView, listener)
    }

    override fun onBindViewHolder(holder: serviceViewHolder, position: Int) {
        holder.nameTV.text = services[position].name
        Glide.with(holder.itemView).load(services[position].icon_url).into(holder.iconIV)

        holder.itemView.setOnClickListener {
            listener.onClick(services[position])
        }
    }


    override fun getItemCount(): Int {
        return services.size
    }

    interface Listener {
        fun onClick(service: Service)
    }
}