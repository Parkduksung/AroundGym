package com.example.aroundgym.data.model.kakao

data class KakaoResponse(
    val documents: List<KakaoSearchItem>,
    val meta: Meta
)