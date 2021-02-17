package com.example.aroundgym.presenter


import android.content.Intent
import android.os.Bundle
import com.example.aroundgym.R
import com.example.aroundgym.base.BaseActivity
import com.example.aroundgym.databinding.ActivityIntroBinding
import com.example.aroundgym.util.ImageUtils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class IntroActivity : BaseActivity<ActivityIntroBinding>(R.layout.activity_intro) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch {
            binding.intro.startAnimation(
                ImageUtils.blinkAnimation(duration = 400L)
            )
            delay(3000)
            startActivity(Intent(this@IntroActivity, GymActivity::class.java))
            this@IntroActivity.finish()
        }

    }
}