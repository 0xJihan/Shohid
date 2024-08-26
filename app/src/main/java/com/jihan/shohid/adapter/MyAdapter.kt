package com.jihan.shohid.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jihan.shohid.R
import com.jihan.shohid.activity.DetailActivity
import com.jihan.shohid.room.Shohid

class MyAdapter(private var shohidList: List<Shohid>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>()
{

    inner class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        val tvName: TextView = itemview.findViewById(R.id.tvName)
        val tvDesc: TextView = itemview.findViewById(R.id.tvDesc)
        val tvDeathTime: TextView = itemview.findViewById(R.id.tvDeathTime)
        val tvId: TextView = itemview.findViewById(R.id.tvId)
        val shohidImage: ImageView = itemview.findViewById(R.id.shohidImage)


        // bind data
        fun bindData(shohid: Shohid,isEnglish:Boolean) {


            // for English Language
            if (isEnglish){
                tvId.text = shohid.id.toString()
                tvName.text = shohid.en_name
                tvDesc.text = "${shohid.en_occupation}\n${shohid.en_description}"
                tvDeathTime.text = shohid.en_date_of_death
                itemView.rootView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra("extra", shohid)
                    startActivity(itemView.context, intent, null)
                }

                if (shohid.img=="null"){
                    shohidImage.setImageResource(R.drawable.placeholder)
                }else{
                    Glide.with(itemView.context).load(shohid.img).placeholder(R.drawable.placeholder).into(shohidImage)
                }

            }


            // for Bangla language
            else {
                tvId.text = shohid.id.toString()
                tvName.text = shohid.name
                tvDesc.text = "${shohid.occupation}\n${shohid.description}"
                tvDeathTime.text = shohid.date_of_death
                itemView.rootView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra("extra", shohid)
                    startActivity(itemView.context, intent, null)
                }

                if (shohid.img=="null"){
                    shohidImage.setImageResource(R.drawable.placeholder)
                }else{
                    Glide.with(itemView.context).load(shohid.img).placeholder(R.drawable.placeholder).into(shohidImage)
                }

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
        val isEnglish = holder.itemView.context.getSharedPreferences("MyPrefs",0).getBoolean("isEnglish",false)

        holder.bindData(shohidList[position],isEnglish)

    }


    // for updating list
    fun updateList(newList: List<Shohid>) {
        this.shohidList = newList
        notifyDataSetChanged()
    }

}