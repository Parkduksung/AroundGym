package com.example.aroundgym.di

import com.example.aroundgym.presenter.GymViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { GymViewModel(get()) }
}