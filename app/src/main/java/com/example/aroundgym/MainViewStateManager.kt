package com.example.aroundgym

import com.example.aroundgym.data.model.Document
import com.example.aroundgym.util.InjectUtil

class MainViewStateManager {

    private val kakaoRepository by lazy { InjectUtil.provideKAKAORepository() }

    private var mainViewState: MainViewState? = null

    fun search(
        bookName: String,
        size: Int = 50,
        sort: String = "accuracy",
        page: Int = 1,
    ) {
        if (bookName.isNotEmpty()) {
            kakaoRepository.searchBook(
                bookName = bookName,
                size = size,
                sort = sort,
                page = page,
                onSuccess = {
                    mainViewState?.search(it.documents)
                },
                onFailure = {
                    mainViewState?.error("Api Error")
                }
            )
        } else {
            mainViewState?.error("검색할 책을 입력하세요.")
        }
    }

    fun setMainViewStateListener(listener: MainViewState) {
        mainViewState = listener
    }

    interface MainViewState {
        fun search(list: List<Document>)
        fun error(message: String)
    }
}


