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
import com.jihan.shohid.utils.LanguageUtils

class SettingFragment : Fragment() {

    private lateinit var binding: FragmentSettingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(inflater)



        val isEnglish = activity?.getSharedPreferences("MyPrefs", MODE_PRIVATE)?.getBoolean("isEnglish",false)

        if (isEnglish!!){
            binding.tikBangla.visibility = View.GONE
        }
        else{
            binding.tikEnglish.visibility = View.GONE
        }





        binding.btEnglish.setOnClickListener{
            val editor = activity?.getSharedPreferences("MyPrefs", MODE_PRIVATE)?.edit()
            editor?.putBoolean("isEnglish",true)
            editor?.apply()

            LanguageUtils(activity!!).setLocale("en")

            (activity as MainActivity).finish()
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btBangla.setOnClickListener{
            val editor = activity?.getSharedPreferences("MyPrefs", MODE_PRIVATE)?.edit()
            editor?.putBoolean("isEnglish",false)
            editor?.apply()

            LanguageUtils(activity!!).setLocale("bn")

            (activity as MainActivity).finish()
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }



        return binding.root
    }
}