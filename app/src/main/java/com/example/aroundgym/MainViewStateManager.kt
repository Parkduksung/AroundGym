package com.example.aroundgym

import com.example.aroundgym.data.model.Document
import com.example.aroundgym.data.repo.KAKAORepository

class MainViewStateManager(private val kakaoRepository: KAKAORepository) {

    private var mainViewState: MainViewState? = null

    fun search(bookName: String) {
        if (bookName.isNotEmpty()) {
            kakaoRepository.searchBook(
                bookName = bookName,
                size = 50,
                sort = "accuracy",
                page = 1,
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


