package com.example.aroundgym.util

import com.example.aroundgym.data.repo.KAKAORepository
import com.example.aroundgym.data.repo.KAKAORepositoryImpl
import com.example.aroundgym.data.source.KAKAORemoteDataSource
import com.example.aroundgym.data.source.KAKAORemoteDataSourceImpl

object InjectUtil {

    fun provideKAKAORepository(): KAKAORepository =
        KAKAORepositoryImpl.getInstance()

    fun provideKAKAORemoteDataSource(): KAKAORemoteDataSource =
        KAKAORemoteDataSourceImpl.getInstance()

}