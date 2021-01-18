package com.example.aroundgym.data.api

import com.example.aroundgym.data.model.kakao.KakaoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoApi {

    companion object {
        private const val REST_API_KEY = "8f44af798eb4792b60dc6068de5d3e11"
        private const val SEARCH = "v2/local/search/keyword.json"
    }


    @Headers("Authorization: KakaoAK $REST_API_KEY")
    @GET(SEARCH)
    fun search(
        @Query("query") keyword: String
    ): Call<KakaoResponse>

}