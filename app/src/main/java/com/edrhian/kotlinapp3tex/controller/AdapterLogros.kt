package com.edrhian.kotlinapp3tex.controller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edrhian.kotlinapp3tex.R
import com.edrhian.kotlinapp3tex.data.Logro


class AdapterLogros(private val achievementsList: ArrayList<Logro>) : RecyclerView.Adapter<AdapterLogros.ViewHolder>(){

    lateinit var context: Context

    var onItemClick : ((Logro) -> Unit)? = null
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var idAchievement : Int = 0
        var achievementName : TextView = itemView.findViewById(R.id.tv_name_achievement)
        //var achievementDesc : TextView = itemView.findViewById(R.id.tv_desc_achievement)
        //var achievementDesc : TextView = itemView.findViewById(R.id.tv_desc_achievement)
        var achievementImg : ImageView = itemView.findViewById(R.id.iv_achievement)
        var achievementUser : Int = 0

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_achievements_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return achievementsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val achievement = achievementsList[position]
        holder.idAchievement = achievement.id
        holder.achievementName.text = achievement.name
        //holder.achievementDesc.text = achievement.desc
        holder.achievementImg.setImageResource(achievement.image)
        holder.achievementUser = achievement.user

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(achievement)
        }
    }

}