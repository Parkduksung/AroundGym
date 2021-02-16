package com.example.aroundgym.presenter

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.example.aroundgym.MainActivity
import com.example.aroundgym.R
import com.example.aroundgym.base.BaseActivity
import com.example.aroundgym.databinding.ActivityIntroBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IntroActivity : BaseActivity<ActivityIntroBinding>(R.layout.activity_intro) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch {
            binding.image.startAnimation(
                AnimationUtils.loadAnimation(
                    this@IntroActivity,
                    R.anim.animation_blink
                )
            )
            delay(3000)
            startActivity(Intent(this@IntroActivity, MainActivity::class.java))
        }
    }
}