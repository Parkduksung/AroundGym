package com.example.aroundgym.data.repo

import com.example.aroundgym.data.model.KakaoSearchBookResponse

interface KAKAORepository {

    fun searchBook(
        bookName: String,
        size: Int,
        sort: String,
        page: Int,
        onSuccess: (response: KakaoSearchBookResponse) -> Unit,
        onFailure: () -> Unit
    )
}