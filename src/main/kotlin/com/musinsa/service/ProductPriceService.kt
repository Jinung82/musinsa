package com.musinsa.service

import com.musinsa.entity.product.ProductPrice
import com.musinsa.exception.MusinsaException
import com.musinsa.repository.ProductPriceRepository
import com.musinsa.type.CategoryType
import com.musinsa.vo.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class ProductPriceService (
    private val productPriceRepository: ProductPriceRepository
) {
    fun findAll() : AllMinCategoryInfoVo {
        val minCategoryPrices = mutableListOf<MinCategoryPriceVo>()
        val aProductPrices =  productPriceRepository.findAll()

        enumValues<CategoryType>().forEach {
            val aProduct = aProductPrices.filter { product -> product.category == it.getCode() }.minBy {productPrice -> productPrice.price }
            minCategoryPrices.add(MinCategoryPriceVo(it.getName(), aProduct.brand, aProduct.price ))
        }
        val totalPrice = getTotalPrice(minCategoryPrices)
        return AllMinCategoryInfoVo(minCategoryPrices, totalPrice)
    }

    private fun getTotalPrice(minCategoryPrices: MutableList<MinCategoryPriceVo>): Long {
        return minCategoryPrices.sumOf { it.price }
    }

    fun findAllByMinPriceBrand() : MinBrandInfoMapperVo {
        val aProductPrices =  productPriceRepository.findAll()
        val brandMap = aProductPrices.groupBy { it.brand }

        val minBrandMap = sortedMapOf<String, Long>()
        brandMap.entries.forEach {
            val productPrices = it.value
            val sumPrice = productPrices.sumOf { it.price }
            minBrandMap[it.key] = sumPrice
        }

        val brandName = minBrandMap.minBy { it.value }.key
        val minPriceValue = minBrandMap.minBy { it.value }.value

        val minBrandPrice =  aProductPrices.filter {  it.brand == brandName }

        val minCategoryPrices = mutableListOf<MinBrandCategoryPriceVo>()
        minBrandPrice.forEach {
            minCategoryPrices.add(MinBrandCategoryPriceVo(it.category, it.price ))
        }
        return MinBrandInfoMapperVo(MinBrandInfoVo(brandName, minCategoryPrices, minPriceValue))
    }

    fun findByMinOrMaxCategory(categoryName : String) : CategoryInfoVo {
        val categoryType = CategoryType.fromCodeName(categoryName)
        val categoryPrices = productPriceRepository.findAllByCategory(categoryType.getCode())
        val aMinCategoryPrice = categoryPrices.minBy { it.price }
        val aMaxCategoryPrice = categoryPrices.maxBy { it.price }
        return CategoryInfoVo(categoryName, CategoryBrandPriceVo(aMinCategoryPrice.brand, aMinCategoryPrice.price ), CategoryBrandPriceVo(aMaxCategoryPrice.brand, aMaxCategoryPrice.price ))
    }

    @Transactional
    fun saveProductPrice(saveProductPriceVo: SaveProductPriceVo)  {
        val categoryType = CategoryType.fromCodeName(saveProductPriceVo.category)
        val aSaveProductPrice = ProductPrice(-1L, categoryType.getCode(), saveProductPriceVo.brand, saveProductPriceVo.price, LocalDateTime.now(), LocalDateTime.now())
        val alreadyProduct = productPriceRepository.findAllByCategoryAndBrand(categoryType.getCode(), saveProductPriceVo.brand)
        if(alreadyProduct == null) {
            productPriceRepository.save(aSaveProductPrice)
        } else {
            throw MusinsaException("이미 등록된 상품 입니다.", "500")
        }
    }

    @Transactional
    fun updateProductPrice(updateProductPriceVo: UpdateProductPriceVo) {
        val categoryType = CategoryType.fromCodeName(updateProductPriceVo.category)
        productPriceRepository.findAllByCategoryAndBrand(categoryType.getCode(), updateProductPriceVo.brand)?.let { it ->
            it.price = updateProductPriceVo.price
            it.dateUpdated = LocalDateTime.now()
            productPriceRepository.save(it)
        } ?: throw MusinsaException("등록된 상품이 없습니다.", "500")
    }
    @Transactional
    fun deleteProductPrice(deleteProductPriceVo: DeleteProductPriceVo) {
        val categoryType = CategoryType.fromCodeName(deleteProductPriceVo.category)
        productPriceRepository.findAllByCategoryAndBrand(categoryType.getCode(), deleteProductPriceVo.brand)?.let { it ->
            productPriceRepository.delete(it)
        } ?: throw MusinsaException("등록된 상품이 없습니다.", "500")
    }

}
