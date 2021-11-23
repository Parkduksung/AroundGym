package com.example.aroundgym.manager

import com.example.aroundgym.data.model.Document
import com.example.aroundgym.util.InjectUtil

class MainViewStateManager {

    private val kakaoRepository by lazy { InjectUtil.provideKAKAORepository() }

    private var mainViewState: MainViewState? = null

    private var pageCount = 1

    private var lastBookName: String? = null


    private fun initConfiguration() {
        pageCount = 1
        lastBookName = null
    }

    fun search(
        bookName: String,
        size: Int = 50,
        sort: String = "accuracy",
    ) {
        initConfiguration()
        if (bookName.isNotEmpty()) {
            kakaoRepository.searchBook(
                bookName = bookName,
                size = size,
                sort = sort,
                page = pageCount,
                onSuccess = {
                    if (!it.meta.is_end) {
                        pageCount++
                        lastBookName = bookName
                    }
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

    fun loadNextData() {
        if (lastBookName != null && pageCount != 1) {
            kakaoRepository.searchBook(
                bookName = lastBookName!!,
                size = 50,
                sort = "accuracy",
                page = pageCount,
                onSuccess = {
                    if (!it.meta.is_end) {
                        pageCount++
                    } else {
                        pageCount = 1
                        lastBookName = null
                        return@searchBook
                    }
                    mainViewState?.loadNextData(it.documents)
                },
                onFailure = {
                    mainViewState?.error("Api Error")
                }
            )
        }
    }

    fun setMainViewStateListener(listener: MainViewState) {
        mainViewState = listener
    }

    interface MainViewState {
        fun search(list: List<Document>)
        fun loadNextData(list: List<Document>)
        fun error(message: String)
    }
}


