package com.musinsa.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class HelloController {
    @GetMapping("/api/hello")
    fun test(): String {
        return "Hello, world!"
    }
}
