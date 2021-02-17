package com.example.aroundgym.presenter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aroundgym.data.api.KakaoApi
import com.example.aroundgym.data.model.kakao.KakaoResponse
import com.example.aroundgym.data.model.kakao.KakaoSearchItem
import com.example.aroundgym.util.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GymViewModel : ViewModel() {

    val kakaoSearch = MutableLiveData<String>()

    private val _kakaoEvent = MutableLiveData<KakaoEvent>()
    val kakaoEvent: LiveData<KakaoEvent> = _kakaoEvent

    fun kakaoSearch() {
        kakaoSearch.value?.let { searchKeyword ->
            Retrofit.builder(baseUrl = Retrofit.KAKAO_BASE_URL).create(KakaoApi::class.java)
                .search(keyword = searchKeyword)
                .enqueue(object : Callback<KakaoResponse> {
                    override fun onResponse(
                        call: Call<KakaoResponse>,
                        response: Response<KakaoResponse>
                    ) {
                        Log.d("결과(성공) - 총갯수 : ", response.body()?.documents?.size.toString())

                        val kakaoSearchList = response.body()?.documents ?: emptyList()

                        _kakaoEvent.value =
                            if (kakaoSearchList.isNotEmpty()) {
                                KakaoEvent.Search(kakaoSearchList)
                            } else {
                                KakaoEvent.NotSearch
                            }
                    }

                    override fun onFailure(call: Call<KakaoResponse>, t: Throwable) {
                        Log.d("결과(실패)  : ", t.message.toString())
                        _kakaoEvent.value = KakaoEvent.NotSearch
                    }
                })
        }
    }
}

sealed class KakaoEvent {
    data class Search(val list: List<KakaoSearchItem>) : KakaoEvent()
    object NotSearch : KakaoEvent()
}