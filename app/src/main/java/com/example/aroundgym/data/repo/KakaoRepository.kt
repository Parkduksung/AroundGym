package com.example.aroundgym.data.repo

import com.example.aroundgym.data.model.kakao.KakaoSearchItem

interface KakaoRepository {
    fun search(
        keyword: String,
        callback: (list: List<KakaoSearchItem>) -> Unit
    )

}


interface KakaoRemoteDataSource {
    fun search(
        keyword: String,
        callback: (list: List<KakaoSearchItem>) -> Unit
    )
}