package com.example.aroundgym

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aroundgym.data.model.SearchItem

class GymAdapter : RecyclerView.Adapter<GymViewHolder>() {

    private val searchList = mutableListOf<SearchItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GymViewHolder =
        GymViewHolder(parent)

    override fun onBindViewHolder(holder: GymViewHolder, position: Int) {
        holder.item(searchList[position])
    }

    override fun getItemCount(): Int =
        searchList.size

    fun getItemList(list: List<SearchItem>) {
        searchList.addAll(list)
    }
}