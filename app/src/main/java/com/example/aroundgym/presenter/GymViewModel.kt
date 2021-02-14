package com.example.aroundgym.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GymViewModel : ViewModel() {

    val kakaoSearch = MutableLiveData<String>()


    fun kakaoSearch() {
        kakaoSearch.value?.let {

        }
    }


}