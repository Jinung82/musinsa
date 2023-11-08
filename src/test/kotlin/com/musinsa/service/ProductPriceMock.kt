package com.musinsa.service

import com.musinsa.entity.product.ProductPrice
import com.musinsa.type.CategoryType
import java.time.LocalDateTime

class ProductPriceMock {
    companion object {
        fun getProductPrice(id : Long, category: CategoryType, brand:String, price : Long) : ProductPrice {
            return ProductPrice(id, category.getCode(), brand, price, LocalDateTime.now(), LocalDateTime.now())
        }
    }
}
