package com.musinsa.vo

data class CategoryInfoVo(
    val 카테고리 : String,
    val 최저가 : CategoryBrandPriceVo,
    val 최고가 : CategoryBrandPriceVo,
)
