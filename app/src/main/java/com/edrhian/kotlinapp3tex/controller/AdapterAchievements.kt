package com.edrhian.kotlinapp3tex.controller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edrhian.kotlinapp3tex.R
import com.edrhian.kotlinapp3tex.data.Achievement


class AdapterAchievements(private val achievementsList: ArrayList<Achievement>) : RecyclerView.Adapter<AdapterAchievements.ViewHolder>(){

    lateinit var context: Context

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var idTarea : Int = 0
        var tareaName : TextView = itemView.findViewById(R.id.tv_name_tarea)
        var tareaDesc : TextView = itemView.findViewById(R.id.tv_desc_tarea)
        var tareaImg : ImageView = itemView.findViewById(R.id.iv_tarea)
        var tareaUser : Int = 0

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_achievements_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return achievementsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tarea = achievementsList[position]
        holder.idTarea = tarea.id
        holder.tareaName.text = tarea.name
        holder.tareaDesc.text = tarea.desc
        holder.tareaImg.setImageResource(tarea.image)
        holder.tareaUser = tarea.user
    }

}