package com.musinsa.type

import com.musinsa.exception.MusinsaException

enum class CategoryType (private val code: String, private val codeName: String) {
    top("top", "상의"),
    outer("outer", "아우터"),
    pants("pants", "바지"),
    sneakers("sneakers", "스니커즈"),
    bag("bag", "가방"),
    hat("hat", "모자"),
    socks("socks", "양말"),
    accessories("accessories", "액세서리");
    fun getCode(): String = code
    fun getName(): String = codeName

    companion object {
        fun fromCodeName(codeName: String): CategoryType {
            return values().firstOrNull {
                it.codeName == codeName
            } ?: throw MusinsaException("$codeName 등록되지 않은 카테고리 입니다.", "500")
        }
    }
}
