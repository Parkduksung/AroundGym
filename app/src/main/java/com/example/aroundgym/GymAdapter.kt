package com.example.aroundgym

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aroundgym.data.model.kakao.KakaoSearchItem
import com.example.aroundgym.data.model.naver.SearchItem
import java.lang.Exception

class GymAdapter : RecyclerView.Adapter<GymViewHolder>() {


    private val searchList = mutableListOf<SearchItem>()
    private val searchKakaoList = mutableListOf<KakaoSearchItem>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GymViewHolder =
        GymViewHolder(parent)

    override fun onBindViewHolder(holder: GymViewHolder, position: Int) {
        holder.item(searchKakaoList[position])
    }

    override fun getItemCount(): Int =
        searchKakaoList.size

    fun getItemList(list: List<SearchItem>) {
        searchList.addAll(list)
    }

    fun getKakaoItemList(list: List<KakaoSearchItem>) {
        searchKakaoList.addAll(list)
        notifyDataSetChanged()
    }


}
