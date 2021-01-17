package com.example.aroundgym

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aroundgym.data.model.kakao.KakaoSearchItem
import com.example.aroundgym.data.model.naver.SearchItem

class GymViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_gym, parent, false)
) {
    private val name = itemView.findViewById<TextView>(R.id.tv_name)
    private val location = itemView.findViewById<TextView>(R.id.tv_location)

    fun item(kakaoSearchItem: KakaoSearchItem) {
        name.text = kakaoSearchItem.place_name
        location.text = kakaoSearchItem.address_name
    }

}