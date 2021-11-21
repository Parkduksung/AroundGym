package com.example.aroundgym.data.source

import com.example.aroundgym.ApiSearchBook
import com.example.aroundgym.data.model.KakaoSearchBookResponse

class KAKAORemoteDataSourceImpl : KAKAORemoteDataSource {

    private val apiSearchBook = ApiSearchBook()

    override fun searchBook(
        bookName: String,
        size: Int,
        sort: String,
        page: Int,
        onSuccess: (response: KakaoSearchBookResponse) -> Unit,
        onFailure: () -> Unit
    ) {
        Thread {
            apiSearchBook.search(bookName, size, sort, page)?.let(onSuccess) ?: onFailure()
        }.start()
    }


    companion object {

        private var INSTANCE: KAKAORemoteDataSource? = null

        fun getInstance(): KAKAORemoteDataSource =
            INSTANCE ?: KAKAORemoteDataSourceImpl().also {
                INSTANCE = it
            }
    }
}