package com.example.aroundgym.data.api

import com.example.aroundgym.data.model.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverApi {

    companion object{
        private const val CLIENT_ID = "oDdLE6xUnLWB5HNPEAhW"
        private const val CLIENT_SECRET = "GI7zHV2wqW"
    }

    @GET("v1/search/local.json")
    fun search(
        @Header("X-Naver-Client-Id") id : String = CLIENT_ID,
        @Header("X-Naver-Client-Secret") secret : String = CLIENT_SECRET,
        @Query("query") search: String,
        @Query("display") count : Int
    ): Call<SearchResponse>
}