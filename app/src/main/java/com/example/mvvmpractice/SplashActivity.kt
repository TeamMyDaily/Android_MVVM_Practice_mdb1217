package com.example.mvvmpractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import com.example.mvvmpractice.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    val SPLASH_VIEW_TIME: Long = 2200
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        binding.apply {
            lifecycleOwner = this@SplashActivity
            activity = this@SplashActivity

            val titleAnim = AnimationUtils.loadAnimation(this@SplashActivity, R.anim.title_anim)
            splashTitle.startAnimation(titleAnim)

            Handler().postDelayed({
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }, SPLASH_VIEW_TIME)
        }
    }
}