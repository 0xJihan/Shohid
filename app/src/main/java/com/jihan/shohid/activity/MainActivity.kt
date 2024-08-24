package com.jihan.shohid.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.jihan.shohid.adapter.ViewPagerAdapter
import com.jihan.shohid.databinding.ActivityMainBinding
import com.jihan.shohid.fragment.Home_Fragment
import com.jihan.shohid.fragment.Search_Fragment

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter : ViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // setting up viewpager
        adapter = ViewPagerAdapter(supportFragmentManager,lifecycle)
        loadFragments()
        binding.root.adapter = adapter
        binding.root.orientation = ViewPager2.ORIENTATION_HORIZONTAL


    }

    // loading fragments
    private fun loadFragments() {
        adapter.addFragment(Home_Fragment())
        adapter.addFragment(Search_Fragment())
    }


}