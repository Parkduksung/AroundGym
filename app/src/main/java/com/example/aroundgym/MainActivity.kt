package com.example.aroundgym

import android.os.Bundle
import android.util.Log
import com.example.aroundgym.base.BaseActivity
import com.example.aroundgym.data.api.NaverApi
import com.example.aroundgym.data.model.SearchResponse
import com.example.aroundgym.databinding.ActivityMainBinding
import com.example.aroundgym.util.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val gymAdapter by lazy { GymAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding.rvGym.run {
            this.adapter = gymAdapter
        }


        binding.btnSearch.setOnClickListener {
            startSearch(binding.etInput.text.toString())
        }

    }

    private fun startSearch(search: String) {
        Retrofit.builder().create(NaverApi::class.java).search(search = search, count = 5)
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
}