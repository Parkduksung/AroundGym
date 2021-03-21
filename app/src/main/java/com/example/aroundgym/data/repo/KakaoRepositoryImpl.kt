package com.example.aroundgym.data.repo

import com.example.aroundgym.data.api.KakaoApi
import com.example.aroundgym.data.model.kakao.KakaoResponse
import com.example.aroundgym.data.model.kakao.KakaoSearchItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KakaoRepositoryImpl(private val kakaoRemoteDataSource: KakaoRemoteDataSource) :
    KakaoRepository {
    override fun search(keyword: String, callback: (list: List<KakaoSearchItem>) -> Unit) {
        kakaoRemoteDataSource.search(keyword, callback)
    }
}

class KakaoRemoteDataSourceImpl(private val kakaoApi: KakaoApi) : KakaoRemoteDataSource {
    override fun search(keyword: String, callback: (list: List<KakaoSearchItem>) -> Unit) {
        kakaoApi.search(keyword = keyword)
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