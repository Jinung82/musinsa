package com.musinsa.service.type

import com.musinsa.exception.MusinsaException
import com.musinsa.type.CategoryType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CategoryTypeTest  {
    @Test
    fun getCategoryType() {
        val categoryType = CategoryType.fromCodeName("상의")
        Assertions.assertNotNull(categoryType)
    }
    @Test
    fun notExistCategoryType() {
        Assertions.assertThrows(MusinsaException::class.java) {CategoryType.fromCodeName("신발")}
    }

}
