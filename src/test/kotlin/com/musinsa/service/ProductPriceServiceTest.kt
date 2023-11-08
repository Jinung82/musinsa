package com.musinsa.service

import com.musinsa.entity.product.ProductPrice
import com.musinsa.exception.MusinsaException
import com.musinsa.main.MockSupportTest
import com.musinsa.repository.ProductPriceRepository
import com.musinsa.type.CategoryType
import com.musinsa.vo.DeleteProductPriceVo
import com.musinsa.vo.SaveProductPriceVo
import com.musinsa.vo.UpdateProductPriceVo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.AdditionalAnswers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.any
import org.mockito.Mockito.times
import java.time.LocalDateTime

class ProductPriceServiceTest :MockSupportTest() {
    @InjectMocks
    lateinit var productPriceService: ProductPriceService

    @Mock
    lateinit var productPriceRepository: ProductPriceRepository

    @Test
    fun findAll() {

        Mockito.`when`(productPriceRepository.findAll()).thenReturn(
            listOf(
                ProductPriceMock.getProductPrice(1, CategoryType.top, "A", 2000),
                ProductPriceMock.getProductPrice(2, CategoryType.outer, "A", 2000),
                ProductPriceMock.getProductPrice(4, CategoryType.pants, "A", 2000),
                ProductPriceMock.getProductPrice(5, CategoryType.sneakers, "A", 2000),
                ProductPriceMock.getProductPrice(6, CategoryType.sneakers, "A", 2000),
                ProductPriceMock.getProductPrice(7, CategoryType.bag, "A", 2000),
                ProductPriceMock.getProductPrice(8, CategoryType.hat, "A", 2000),
                ProductPriceMock.getProductPrice(9, CategoryType.socks, "A", 2000),
                ProductPriceMock.getProductPrice(10, CategoryType.accessories, "A", 2000),
            )
        )

        val productPrices = productPriceService.findAll()
        Assertions.assertNotNull(productPrices)
        Assertions.assertEquals(16000, productPrices.총액)
    }

    @Test
    fun findAllException() {
        Mockito.`when`(productPriceRepository.findAll()).thenReturn(
            listOf(
                ProductPriceMock.getProductPrice(1, CategoryType.top, "A", 2000),
                ProductPriceMock.getProductPrice(2, CategoryType.outer, "A", 2000),
                ProductPriceMock.getProductPrice(4, CategoryType.pants, "A", 2000),
                ProductPriceMock.getProductPrice(5, CategoryType.sneakers, "A", 2000),
                ProductPriceMock.getProductPrice(6, CategoryType.sneakers, "A", 2000),
                ProductPriceMock.getProductPrice(7, CategoryType.bag, "A", 2000),
                ProductPriceMock.getProductPrice(8, CategoryType.hat, "A", 2000),
                ProductPriceMock.getProductPrice(9, CategoryType.socks, "A", 2000),
            )
        )
        Assertions.assertThrows(NoSuchElementException::class.java) {
            productPriceService.findAll()
        }
    }

    @Test
    fun findAllByMinPriceBrand() {
        Mockito.`when`(productPriceRepository.findAll()).thenReturn(
            listOf(
                ProductPriceMock.getProductPrice(1, CategoryType.top, "A", 2000),
                ProductPriceMock.getProductPrice(2, CategoryType.outer, "A", 2000),
                ProductPriceMock.getProductPrice(4, CategoryType.pants, "A", 2000),
                ProductPriceMock.getProductPrice(5, CategoryType.sneakers, "A", 2000),
                ProductPriceMock.getProductPrice(6, CategoryType.sneakers, "A", 2000),
                ProductPriceMock.getProductPrice(7, CategoryType.bag, "A", 2000),
                ProductPriceMock.getProductPrice(8, CategoryType.hat, "A", 2000),
                ProductPriceMock.getProductPrice(9, CategoryType.socks, "A", 2000),
                ProductPriceMock.getProductPrice(10, CategoryType.accessories, "A", 2000),
                ProductPriceMock.getProductPrice(1, CategoryType.top, "B", 1000),
                ProductPriceMock.getProductPrice(2, CategoryType.outer, "B", 1000),
                ProductPriceMock.getProductPrice(4, CategoryType.pants, "B", 1000),
                ProductPriceMock.getProductPrice(5, CategoryType.sneakers, "B", 1000),
                ProductPriceMock.getProductPrice(6, CategoryType.sneakers, "B", 1000),
                ProductPriceMock.getProductPrice(7, CategoryType.bag, "B", 1000),
                ProductPriceMock.getProductPrice(8, CategoryType.hat, "B", 1000),
                ProductPriceMock.getProductPrice(9, CategoryType.socks, "B", 1000),
                ProductPriceMock.getProductPrice(10, CategoryType.accessories, "B", 1000),
            )
        )
        val brandProductPrice = productPriceService.findAllByMinPriceBrand()
        Assertions.assertNotNull(brandProductPrice)
        Assertions.assertEquals("B", brandProductPrice.최저가.브렌드)
        Assertions.assertEquals(9000, brandProductPrice.최저가.총액)
    }

    @Test
    fun findByMinOrMaxCategory() {
        Mockito.`when`(productPriceRepository.findAllByCategory(CategoryType.top.getCode())).thenReturn(
            listOf(
                ProductPriceMock.getProductPrice(1, CategoryType.top, "A", 200000),
                ProductPriceMock.getProductPrice(2, CategoryType.top, "B", 2000),
                ProductPriceMock.getProductPrice(4, CategoryType.top, "C", 2000),
                ProductPriceMock.getProductPrice(5, CategoryType.top, "D", 2000),
                ProductPriceMock.getProductPrice(6, CategoryType.top, "E", 2000),
                ProductPriceMock.getProductPrice(7, CategoryType.top, "F", 2000),
                ProductPriceMock.getProductPrice(8, CategoryType.top, "G", 2000),
                ProductPriceMock.getProductPrice(9, CategoryType.top, "H", 2000),
                ProductPriceMock.getProductPrice(10, CategoryType.top, "I", 1000),
            )
        )
        val minOrMaxProductPrice = productPriceService.findByMinOrMaxCategory("상의")
        Assertions.assertEquals("I", minOrMaxProductPrice.최저가.브렌드)
        Assertions.assertEquals("A", minOrMaxProductPrice.최고가.브렌드)
    }

    @Test
    fun alreadySaveProductPrice() {
        Mockito.`when`(productPriceRepository.findAllByCategoryAndBrand(CategoryType.top.getCode(), "B")).thenReturn(
                ProductPriceMock.getProductPrice(2, CategoryType.top, "B", 2000),
        )
        val saveProductPriceVo  = SaveProductPriceVo( "상의", "B", 2000)
        Assertions.assertThrows(MusinsaException::class.java) {productPriceService.saveProductPrice(saveProductPriceVo)}
    }

    @Test
    fun newSaveProductPrice() {
        Mockito.`when`(productPriceRepository.findAllByCategoryAndBrand(CategoryType.top.getCode(), "B")).thenReturn(
            null
        )
        val saveProductPriceVo  = SaveProductPriceVo( "상의", "B", 2000)
        Mockito.`when`(productPriceRepository.save(any(ProductPrice::class.java))).then(AdditionalAnswers.returnsFirstArg<ProductPrice>())
        val saveProductPrice = productPriceService.saveProductPrice(saveProductPriceVo)
        Assertions.assertNotNull(saveProductPrice)
    }

    @Test
    fun nullUpdateProductPrice() {
        Mockito.`when`(productPriceRepository.findAllByCategoryAndBrand(CategoryType.top.getCode(), "B")).thenReturn(
            null,
        )
        val updateProductPriceVo  = UpdateProductPriceVo( "상의", "B", 2000)
        Assertions.assertThrows(MusinsaException::class.java) {productPriceService.updateProductPrice(updateProductPriceVo)}
    }

    @Test
    fun notNullUpdateProductPrice() {
        Mockito.`when`(productPriceRepository.findAllByCategoryAndBrand(CategoryType.top.getCode(), "B")).thenReturn(
            ProductPriceMock.getProductPrice(2, CategoryType.top, "B", 2000),
        )
        val updateProductPriceVo  = UpdateProductPriceVo( "상의", "B", 2000)
        Mockito.`when`(productPriceRepository.save(any(ProductPrice::class.java))).then(AdditionalAnswers.returnsFirstArg<ProductPrice>())
        val updateProductPrice = productPriceService.updateProductPrice(updateProductPriceVo)
        Assertions.assertNotNull(updateProductPrice)
    }

    @Test
    fun nullDeleteProductPrice() {
        Mockito.`when`(productPriceRepository.findAllByCategoryAndBrand(CategoryType.top.getCode(), "B")).thenReturn(
            null,
        )
        val deleteProductPriceVo  = DeleteProductPriceVo( "상의", "B", 2000)
        Assertions.assertThrows(MusinsaException::class.java) {productPriceService.deleteProductPrice(deleteProductPriceVo)}
    }

    @Test
    fun notNullDeleteProductPrice() {
        Mockito.`when`(productPriceRepository.findAllByCategoryAndBrand(CategoryType.top.getCode(), "B")).thenReturn(
            ProductPriceMock.getProductPrice(2, CategoryType.top, "B", 2000),
        )
        val deleteProductPriceVo  = DeleteProductPriceVo( "상의", "B", 2000)
        val deleteProductPrice = productPriceService.deleteProductPrice(deleteProductPriceVo)
        Assertions.assertNotNull(deleteProductPrice)
    }

}
