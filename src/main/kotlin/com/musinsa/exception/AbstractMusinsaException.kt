package com.musinsa.exception
abstract class AbstractMusinsaException(
    message: String,
    code: String,
    cause: Throwable? = null,
) : RuntimeException(message, cause) {
    val code: String = code
}

