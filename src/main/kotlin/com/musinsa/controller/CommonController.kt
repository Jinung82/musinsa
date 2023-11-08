package com.musinsa.controller

import com.musinsa.exception.MusinsaException
import org.json.JSONObject
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler


abstract class CommonController {
    @ExceptionHandler(MusinsaException::class)
    fun handleNoSuchElementFoundException(exception: MusinsaException): ResponseEntity<String?> {
        val jsonObject = JSONObject()
        jsonObject.put("rsltCd", exception.code)
        jsonObject.put("rsltMsg", exception.localizedMessage)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonObject.toString())
    }
}
