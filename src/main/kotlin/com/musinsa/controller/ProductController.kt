package com.musinsa.controller

import com.musinsa.service.ProductPriceService
import com.musinsa.vo.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController  (
    private val productPriceService: ProductPriceService
) : CommonController() {

    @RequestMapping("/api/products/all")
    fun findAll() : ResponseEntity<AllMinCategoryInfoVo> {
        val result = productPriceService.findAll()
        return ResponseEntity.ok().body(result)
    }

    @RequestMapping("/api/products/brand")
    fun minPriceBrand() : ResponseEntity<MinBrandInfoMapperVo> {
        val result = productPriceService.findAllByMinPriceBrand()
        return ResponseEntity.ok().body(result)
    }

    @RequestMapping("/api/products/category/{categoryName}")
    fun minCategoryPrice(@PathVariable categoryName : String) : ResponseEntity<CategoryInfoVo> {
        val result = productPriceService.findByMinOrMaxCategory(categoryName)
        return ResponseEntity.ok().body(result)
    }

    @PostMapping("/api/product/save")
    fun save(@RequestBody saveProductPriceVo: SaveProductPriceVo) : ResponseEntity<String> {
        productPriceService.saveProductPrice(saveProductPriceVo)
        return ResponseEntity.ok().body("상품이 정상적 으로 등록 되었습니다.")
    }

    @PostMapping("/api/product/update")
    fun update(@RequestBody updateProductPriceVo: UpdateProductPriceVo) : ResponseEntity<String> {
        productPriceService.updateProductPrice(updateProductPriceVo)
        return ResponseEntity.ok().body("상품의 가격이 수정 되었습니다.")
    }

    @PostMapping("/api/product/delete")
    fun delete(@RequestBody deleteProductPriceVo: DeleteProductPriceVo) : ResponseEntity<String> {
        productPriceService.deleteProductPrice(deleteProductPriceVo)
        return ResponseEntity.ok().body("상품이 삭제 되었습니다.")
    }

}
