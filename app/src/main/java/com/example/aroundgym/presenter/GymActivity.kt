package com.example.aroundgym.presenter

import android.os.Bundle
import android.util.Log
import com.example.aroundgym.GymAdapter
import com.example.aroundgym.R
import com.example.aroundgym.base.BaseActivity
import com.example.aroundgym.data.api.KakaoApi
import com.example.aroundgym.data.api.NaverApi
import com.example.aroundgym.data.model.kakao.KakaoResponse
import com.example.aroundgym.data.model.naver.SearchResponse
import com.example.aroundgym.databinding.ActivityMainBinding
import com.example.aroundgym.util.Retrofit
import com.example.aroundgym.util.Retrofit.KAKAO_BASE_URL
import com.example.aroundgym.util.Retrofit.NAVER_BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GymActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val gymAdapter by lazy { GymAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding.rvGym.run {
            this.adapter = gymAdapter
        }


        binding.btnSearch.setOnClickListener {
            startKakaoSearch(binding.etInput.text.toString())
        }

    }

    private fun startNaverSearch(search: String) {
        Retrofit.builder(baseUrl = NAVER_BASE_URL).create(NaverApi::class.java)
            .search(search = search, count = 5)
            .enqueue(object : Callback<SearchResponse> {
                override fun onResponse(
                    call: Call<SearchResponse>,
                    response: Response<SearchResponse>
                ) {
                    Log.d("결과(성공) - 총갯수 : ", response.body()?.items?.size.toString())
                    response.body()?.items?.let { gymAdapter.getItemList(it) }
                }

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    Log.d("결과(실패) - 총갯수 : ", t.message.toString())
                }
            })
    }


    private fun startKakaoSearch(searchKeyword: String) {
        Retrofit.builder(baseUrl = KAKAO_BASE_URL).create(KakaoApi::class.java)
            .search(keyword = searchKeyword)
            .enqueue(object : Callback<KakaoResponse> {
                override fun onResponse(
                    call: Call<KakaoResponse>,
                    response: Response<KakaoResponse>
                ) {
                    Log.d("결과(성공) - 총갯수 : ", response.body()?.documents?.size.toString())

                    response.body()?.documents?.forEach {
                        Log.d("결과(성공) - 이름 : ", it.place_name)
                        Log.d("결과(성공) - 주소 : ", it.road_address_name)
                    }

                    response.body()?.documents?.let { gymAdapter.getKakaoItemList(it) }
                }

                override fun onFailure(call: Call<KakaoResponse>, t: Throwable) {
                    Log.d("결과(실패)  : ", t.message.toString())
                }
            })

    }
}