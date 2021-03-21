package com.example.aroundgym.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aroundgym.data.model.kakao.KakaoSearchItem
import com.example.aroundgym.data.repo.KakaoRepository

class GymViewModel(private val kakaoRepository: KakaoRepository) : ViewModel() {

    val kakaoSearch = MutableLiveData<String>()

    private val _kakaoEvent = MutableLiveData<KakaoEvent>()
    val kakaoEvent: LiveData<KakaoEvent> = _kakaoEvent

    fun kakaoSearch() {
        kakaoSearch.value?.let { searchKeyword ->

            kakaoRepository.search(searchKeyword, callback = { kakaoSearchList ->
                _kakaoEvent.value =
                    if (kakaoSearchList.isNotEmpty()) {
                        KakaoEvent.Search(kakaoSearchList)
                    } else {
                        KakaoEvent.NotSearch
                    }
            })
        }
    }
}

sealed class KakaoEvent {
    data class Search(val list: List<KakaoSearchItem>) : KakaoEvent()
    object NotSearch : KakaoEvent()
}