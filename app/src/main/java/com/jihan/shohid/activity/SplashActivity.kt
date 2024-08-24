package com.jihan.shohid.activity

import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.LinearInterpolator
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jihan.shohid.R
import com.jihan.shohid.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var splashScreen: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        splashScreen = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashScreen.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }





        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        },4000)

        startColorTransitionAnimation(splashScreen.root)

    }

    private fun startColorTransitionAnimation(view: android.view.View) {
        // Initial color (you can change this to your current background color)
        val initialColor = Color.parseColor("#FFFFFF") // White

        // Final color (red)
        val finalColor = Color.RED

        // Create a ValueAnimator to animate the color change
        val colorAnimator = ValueAnimator.ofArgb(initialColor, finalColor).apply {
            duration = 5000 // 6 seconds
            interpolator = LinearInterpolator() // Smooth transition
            addUpdateListener { animator ->
                val animatedColor = animator.animatedValue as Int
                view.setBackgroundColor(animatedColor)
            }
        }

        // Start the animation
        colorAnimator.start()
    }
}