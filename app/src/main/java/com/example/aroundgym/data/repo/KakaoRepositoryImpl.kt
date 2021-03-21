package com.example.aroundgym.data.repo

import com.example.aroundgym.data.api.KakaoApi
import com.example.aroundgym.data.model.kakao.KakaoResponse
import com.example.aroundgym.data.model.kakao.KakaoSearchItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KakaoRepositoryImpl : KakaoRepository {
    override fun search(keyword: String, callback: (list: List<KakaoSearchItem>) -> Unit) {
        com.example.aroundgym.util.Retrofit.builder(baseUrl = com.example.aroundgym.util.Retrofit.KAKAO_BASE_URL)
            .create(KakaoApi::class.java)
            .search(keyword = keyword)
            .enqueue(object : Callback<KakaoResponse> {
                override fun onResponse(
                    call: Call<KakaoResponse>,
                    response: Response<KakaoResponse>
                ) {
                    callback(response.body()?.documents ?: emptyList())
                }

                override fun onFailure(call: Call<KakaoResponse>, t: Throwable) {
                    callback(emptyList())
                }
            })
    }
}