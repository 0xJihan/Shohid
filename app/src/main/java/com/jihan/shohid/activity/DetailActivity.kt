package com.jihan.shohid.activity

import com.jihan.shohid.room.Shohid
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jihan.shohid.R
import com.jihan.shohid.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
//
//        val intent = intent
//        val shohid = intent.getSerializableExtra("SHOHID_EXTRA") as? Shohid

//
//        if (shohid != null) {
//            if (shohid.age.isNullOrEmpty()) {
//                binding.age.text = "বয়স : ----------"
//            } else {
//                binding.age.text = "বয়স : ${shohid.age}"
//            }
//
//            if (shohid.dob == null) {
//                binding.dob.text = "জন্ম তারিখ : ----------"
//            } else {
//                binding.dob.text = "জন্ম তারিখ : ${shohid.dob}"
//            }
//
//            if (shohid.birth_place.isNullOrEmpty()) {
//                binding.birthPlace.text = "জন্মস্থান : ----------"
//            } else {
//                binding.birthPlace.text = "জন্মস্থান : ${shohid.birth_place}"
//            }
//
//            binding.name.text = shohid.name
//            binding.descriptin.text =
//                "${shohid.occupation}\n ${shohid.description}\nমৃত্যু তারিখ : ${shohid.date_of_death}"
//            binding.reason.text = shohid.reason
//            binding.personalLife.text = shohid.personal_life
//        }


    }
}