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

        val isEnglish = getSharedPreferences("MyPrefs", 0).getBoolean("isEnglish", false)

        val intent = intent
        val shohid = intent.getSerializableExtra("extra") as? Shohid


        if (shohid != null) {

            // for english language
            if (isEnglish) {

                if (shohid.img == "null") {
                    binding.imageView.setImageResource(R.drawable.placeholder)
                } else {
                    Glide.with(this).load(shohid.img).placeholder(R.drawable.placeholder)
                        .into(binding.imageView)
                }

                if (shohid.age == "null") {
                    binding.age.text = "Age : ----------"
                } else {
                    binding.age.text = "Age : ${shohid.en_age}"
                }

                if (shohid.dob == "null") {
                    binding.dob.text = "Birth Date : ----------"
                } else {
                    binding.dob.text = "Birth Date : ${shohid.en_dob}"
                }

                if (shohid.birth_place == "null") {
                    binding.birthPlace.text = "Birth Place : ----------"
                } else {
                    binding.birthPlace.text = "Birth Place : ${shohid.en_birth_place}"
                }

                binding.name.text = shohid.en_name
                binding.descriptin.text =
                    "${shohid.en_occupation}\n ${shohid.en_description}\nDate of Death : ${shohid.en_date_of_death}"
                binding.reason.text = shohid.en_reason
                binding.personalLife.text = shohid.en_personal_life
            }


            // for bangla language
            else {

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

}


