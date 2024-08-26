package com.jihan.shohid.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.fragment.app.Fragment
import com.jihan.shohid.activity.MainActivity
import com.jihan.shohid.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

    private lateinit var binding: FragmentSettingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(inflater)


        binding.button.setOnClickListener{
            val editor = activity?.getSharedPreferences("MyPrefs", MODE_PRIVATE)?.edit()
            editor?.putBoolean("isEnglish",true)
            editor?.apply()
            (activity as MainActivity).finish()
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)

        }

        binding.button2.setOnClickListener{
            val editor = activity?.getSharedPreferences("MyPrefs", MODE_PRIVATE)?.edit()
            editor?.putBoolean("isEnglish",false)
            editor?.apply()
            (activity as MainActivity).finish()
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }



        return binding.root
    }
}