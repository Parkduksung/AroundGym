package com.example.aroundgym.data.source

import com.example.aroundgym.data.model.KakaoSearchBookResponse

interface KAKAORemoteDataSource {

    fun searchBook(
        bookName: String,
        size: Int,
        sort: String,
        page: Int,
        onSuccess: (response: KakaoSearchBookResponse) -> Unit,
        onFailure: () -> Unit
    )
}