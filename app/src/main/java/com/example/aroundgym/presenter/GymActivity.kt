package com.example.aroundgym.presenter

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.aroundgym.GymAdapter
import com.example.aroundgym.R
import com.example.aroundgym.base.BaseActivity
import com.example.aroundgym.databinding.ActivityMainBinding

class GymActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val gymAdapter by lazy { GymAdapter() }

    private lateinit var gymViewModel: GymViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gymViewModel = ViewModelProvider(this).get(GymViewModel::class.java)

        binding.rvGym.run {
            this.adapter = gymAdapter
        }

        binding.run {
            viewModel = gymViewModel
            lifecycleOwner = this@GymActivity
        }

    }


}