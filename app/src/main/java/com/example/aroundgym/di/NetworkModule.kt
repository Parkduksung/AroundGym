package com.example.aroundgym.di

import com.example.aroundgym.data.api.KakaoApi
import com.example.aroundgym.util.Retrofit
import org.koin.dsl.module


val networkModule = module {
    single<KakaoApi> {
        Retrofit.builder(baseUrl = Retrofit.KAKAO_BASE_URL)
            .create(KakaoApi::class.java)
    }
}