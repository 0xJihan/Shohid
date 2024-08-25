package com.jihan.shohid.fragment

import NetworkUtils
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.jihan.shohid.MyApplication
import com.jihan.shohid.R
import com.jihan.shohid.databinding.FragmentRandomBinding
import com.jihan.shohid.model.ShohidViewModel
import com.jihan.shohid.model.ViewModelFactory
import com.jihan.shohid.room.Shohid


class RandomShohid : Fragment() {

    private lateinit var binding: FragmentRandomBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRandomBinding.inflate(layoutInflater)


        val repository = (activity?.application as MyApplication).repository

        //viewModel
        val viewModel =
            ViewModelProvider(this, ViewModelFactory(repository))[ShohidViewModel::class.java]


        val shohid = viewModel.randomShohid.value


        if (shohid != null) {
            bindData(shohid)
        }

        viewModel.randomShohid.observe(viewLifecycleOwner) {
            bindData(it)
        }



        binding.root.setOnRefreshListener {
            viewModel.refreshRandomShohid()
            binding.root.isRefreshing = false
        }



        return binding.root
    }


    private fun bindData(shohid: Shohid) {
        if (shohid.img == "null" || !NetworkUtils().isInternetConnected(requireContext())) {
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