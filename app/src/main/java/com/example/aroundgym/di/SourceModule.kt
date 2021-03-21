package com.example.aroundgym.di

import com.example.aroundgym.data.repo.KakaoRemoteDataSource
import com.example.aroundgym.data.repo.KakaoRemoteDataSourceImpl
import org.koin.dsl.module


val sourceModule = module {
    single<KakaoRemoteDataSource> { KakaoRemoteDataSourceImpl(get()) }
}