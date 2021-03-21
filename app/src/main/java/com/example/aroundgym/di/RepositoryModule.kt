package com.example.aroundgym.di

import com.example.aroundgym.data.repo.KakaoRepository
import com.example.aroundgym.data.repo.KakaoRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<KakaoRepository> { KakaoRepositoryImpl(get()) }
}