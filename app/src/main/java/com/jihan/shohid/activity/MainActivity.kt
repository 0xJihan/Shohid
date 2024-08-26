package com.jihan.shohid.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.jihan.shohid.R
import com.jihan.shohid.adapter.ViewPagerAdapter
import com.jihan.shohid.databinding.ActivityMainBinding
import com.jihan.shohid.fragment.Home_Fragment
import com.jihan.shohid.fragment.RandomShohid
import com.jihan.shohid.fragment.SettingFragment
import com.jihan.shohid.utils.LanguageUtils

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        LanguageUtils(this).loadLocale()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // setting up viewpager
        adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        loadFragments()
        binding.viewPager2.adapter = adapter
        binding.viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL


        // Optionally, sync ViewPager2 to BottomNavigationView when user swipes
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {

                }
            }
        })

        binding.bottomBar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
               R.id.item2 -> binding.viewPager2.currentItem = 0
                R.id.item3 -> binding.viewPager2.currentItem = 1
            }
            true
        }

        // Optionally, sync ViewPager2 to BottomNavigationView when user swipes
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> binding.bottomBar.selectedItemId = R.id.item2
                    1 -> binding.bottomBar.selectedItemId = R.id.item3
                }
            }
        })
        val text = getSharedPreferences("MyPrefs", MODE_PRIVATE).getBoolean("isEnglish",false)
        Toast.makeText(this, "$text", Toast.LENGTH_SHORT).show()


    }

    // loading fragments
    private fun loadFragments() {
        adapter.addFragment(Home_Fragment())
        adapter.addFragment(RandomShohid())
        adapter.addFragment(SettingFragment())
    }


}