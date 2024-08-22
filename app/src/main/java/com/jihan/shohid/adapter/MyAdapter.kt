package com.jihan.shohid.adapter

import Shohid
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.jihan.shohid.R
import com.jihan.shohid.activity.DetailActivity

class MyAdapter(private val shohidList: List<Shohid>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        val tvName: TextView = itemview.findViewById(R.id.tvName)
        val tvDesc: TextView = itemview.findViewById(R.id.tvDesc)
        val tvDeathTime: TextView = itemview.findViewById(R.id.tvDeathTime)
        val tvId: TextView = itemview.findViewById(R.id.tvId)

        fun bindData(shohid: Shohid) {
            tvId.text = shohid.id.toString()
            tvName.text = shohid.name
            tvDesc.text = "${shohid.occupation}\n${shohid.description}"
            tvDeathTime.text = shohid.date_of_death
            itemView.rootView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("SHOHID_EXTRA", shohid)
                startActivity(itemView.context, intent, null)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)

        return MyViewHolder(view)


    }

    override fun getItemCount(): Int {
        if (shohidList == null || shohidList.isEmpty() || shohidList.size == 0) {
            return 0
        } else return shohidList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bindData(shohidList[position])

    }
}