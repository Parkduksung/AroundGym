package com.example.aroundgym.presenter

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.aroundgym.GymAdapter
import com.example.aroundgym.R
import com.example.aroundgym.base.BaseActivity
import com.example.aroundgym.databinding.ActivityMainBinding

class GymActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val gymAdapter by lazy { GymAdapter() }

    private val gymViewModel by lazy { ViewModelProvider(this).get(GymViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.rvGym.run {
            this.adapter = gymAdapter
        }

        binding.run {
            viewModel = gymViewModel
            lifecycleOwner = this@GymActivity
        }

        observationKakaoEvent()
    }

    private fun observationKakaoEvent() {
        gymViewModel.kakaoEvent.observe(this, { kakaoEvent ->
            when (kakaoEvent) {
                is KakaoEvent.Search -> {
                    gymAdapter.getKakaoItemList(kakaoEvent.list)
                }
                is KakaoEvent.NotSearch -> {
                    Toast.makeText(this, "찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}