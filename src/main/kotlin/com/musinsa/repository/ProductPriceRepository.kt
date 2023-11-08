package com.musinsa.repository

import com.musinsa.entity.product.ProductPrice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductPriceRepository : JpaRepository<ProductPrice, Long> {
    fun findAllByCategory(categoryName : String) : List<ProductPrice>
    fun findAllByCategoryAndBrand(categoryName : String, brand : String) : ProductPrice?

}
