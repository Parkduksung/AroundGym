package com.example.aroundgym.data.repo

import com.example.aroundgym.data.model.KakaoSearchBookResponse
import com.example.aroundgym.data.source.KAKAORemoteDataSource

class KAKAORepositoryImpl(private val kakaoRemoteDataSource: KAKAORemoteDataSource) :
    KAKAORepository {

    override fun searchBook(
        bookName: String,
        size: Int,
        sort: String,
        page: Int,
        onSuccess: (response: KakaoSearchBookResponse) -> Unit,
        onFailure: () -> Unit
    ) {
        kakaoRemoteDataSource.searchBook(bookName, size, sort, page, onSuccess, onFailure)
    }


    companion object {

        private var INSTANCE: KAKAORepository? = null

        fun getInstance(kakaoRemoteDataSource: KAKAORemoteDataSource): KAKAORepository =
            INSTANCE ?: KAKAORepositoryImpl(kakaoRemoteDataSource).also {
                INSTANCE = it
            }

    }
}