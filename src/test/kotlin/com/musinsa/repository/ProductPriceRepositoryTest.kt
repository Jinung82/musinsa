package com.musinsa.repository

import com.musinsa.entity.product.ProductPrice
import com.musinsa.main.SpringSupportTest
import com.musinsa.type.CategoryType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Transactional
class ProductPriceRepositoryTest (
    val productPriceRepository: ProductPriceRepository
) :  SpringSupportTest() {

    @Test
    fun getProductPriceAll() {
        val productPrices =  productPriceRepository.findAll()
        Assertions.assertNotNull(productPrices)
    }

    @Test
    fun findAllByCategory()  {
        enumValues<CategoryType>().forEach {
            val topProductPrice = productPriceRepository.findAllByCategory(it.getCode())
            Assertions.assertNotNull(topProductPrice)
        }
    }
    @Test
    fun findAllByCategoryAndBrandNotNull() {
        val categoryType = CategoryType.fromCodeName("상의")
        val notNullProductPrice = productPriceRepository.findAllByCategoryAndBrand(categoryType.getCode(), "B")
        Assertions.assertNotNull(notNullProductPrice)
    }

    @Test
    fun findAllByCategoryAndBrandNull() {
        val categoryType = CategoryType.fromCodeName("상의")
        val notNullProductPrice = productPriceRepository.findAllByCategoryAndBrand(categoryType.getCode(), "L")
        Assertions.assertNull(notNullProductPrice)
    }
    @Test
    fun save() {
        val productPrice = ProductPrice(-1, CategoryType.top.getCode(), "B", 1000, LocalDateTime.now(), LocalDateTime.now())
        val saveProductPrice = productPriceRepository.save(productPrice)
        Assertions.assertEquals(1000, saveProductPrice.price)
        Assertions.assertEquals(CategoryType.top.getCode(), saveProductPrice.category)
    }
}
