package com.example.aroundgym.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    const val NAVER_BASE_URL = "https://openapi.naver.com/"
    const val KAKAO_BASE_URL = "https://dapi.kakao.com/"
    fun builder(baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}