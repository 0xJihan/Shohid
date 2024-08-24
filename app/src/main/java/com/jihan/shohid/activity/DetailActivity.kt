package com.jihan.shohid.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.jihan.shohid.R
import com.jihan.shohid.databinding.ActivityDetailBinding
import com.jihan.shohid.room.Shohid

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val shohid = intent.getSerializableExtra("extra") as? Shohid


        if (shohid != null) {

            if (shohid.img == "null") {
                binding.imageView.setImageResource(R.drawable.placeholder)
            } else {
                Glide.with(this).load(shohid.img).placeholder(R.drawable.placeholder)
                    .into(binding.imageView)
            }

            if (shohid.age == "null") {
                binding.age.text = "বয়স : ----------"
            } else {
                binding.age.text = "বয়স : ${shohid.age}"
            }

            if (shohid.dob == "null") {
                binding.dob.text = "জন্ম তারিখ : ----------"
            } else {
                binding.dob.text = "জন্ম তারিখ : ${shohid.dob}"
            }

            if (shohid.birth_place == "null") {
                binding.birthPlace.text = "জন্মস্থান : ----------"
            } else {
                binding.birthPlace.text = "জন্মস্থান : ${shohid.birth_place}"
            }

            binding.name.text = shohid.name
            binding.descriptin.text =
                "${shohid.occupation}\n ${shohid.description}\nমৃত্যু তারিখ : ${shohid.date_of_death}"
            binding.reason.text = shohid.reason
            binding.personalLife.text = shohid.personal_life
        }


    }


}