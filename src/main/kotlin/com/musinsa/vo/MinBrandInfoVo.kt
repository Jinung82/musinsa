package com.musinsa.vo

data class MinBrandInfoVo(
    val 브렌드 : String,
    val 카테고리 : MutableList<MinBrandCategoryPriceVo>,
    val 총액 : Long,
) {
}
