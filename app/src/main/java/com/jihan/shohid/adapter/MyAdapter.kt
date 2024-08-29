package com.jihan.shohid.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jihan.shohid.R
import com.jihan.shohid.activity.DetailActivity
import com.jihan.shohid.room.Shohid

class MyAdapter  : ListAdapter<Shohid, MyAdapter.MyViewHolder>(ShohidDiffCallback()) {

    inner class MyViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
    ) {
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvDesc: TextView = itemView.findViewById(R.id.tvDesc)
        private val tvDeathTime: TextView = itemView.findViewById(R.id.tvDeathTime)
        private val tvId: TextView = itemView.findViewById(R.id.tvId)
        private val shohidImage: ImageView = itemView.findViewById(R.id.shohidImage)

        fun bind(shohid: Shohid) {
            val isEnglish = itemView.context.getSharedPreferences("MyPrefs", 0).getBoolean("isEnglish", false)

            if (isEnglish) {
                tvId.text = shohid.id.toString()
                tvName.text = shohid.en_name
                tvDesc.text = "${shohid.en_occupation}\n${shohid.en_description}"
                tvDeathTime.text = shohid.en_date_of_death
            } else {
                tvId.text = shohid.id.toString()
                tvName.text = shohid.name
                tvDesc.text = "${shohid.occupation}\n${shohid.description}"
                tvDeathTime.text = shohid.date_of_death
            }

            if (shohid.img == "null") {
                shohidImage.setImageResource(R.drawable.placeholder)
            } else {
                Glide.with(itemView.context)
                    .load(shohid.img)
                    .placeholder(R.drawable.placeholder)
                    .into(shohidImage)
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java).apply {
                    putExtra("extra", shohid)
                }
                startActivity(itemView.context, intent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class ShohidDiffCallback : DiffUtil.ItemCallback<Shohid>() {
        override fun areItemsTheSame(oldItem: Shohid, newItem: Shohid): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Shohid, newItem: Shohid): Boolean {
            return oldItem == newItem
        }
    }
}
