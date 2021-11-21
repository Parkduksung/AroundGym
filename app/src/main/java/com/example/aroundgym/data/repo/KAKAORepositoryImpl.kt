package com.example.aroundgym.data.repo

import com.example.aroundgym.data.model.KakaoSearchBookResponse
import com.example.aroundgym.util.InjectUtil

class KAKAORepositoryImpl : KAKAORepository {

    private val kakaoRemoteDataSource by lazy { InjectUtil.provideKAKAORemoteDataSource() }

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

        fun getInstance(): KAKAORepository =
            INSTANCE ?: KAKAORepositoryImpl().also {
                INSTANCE = it
            }

    }
}