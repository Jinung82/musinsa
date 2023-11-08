package com.musinsa.exception

class MusinsaException (
    message: String,
    code: String,
    cause: Throwable? = null
) : AbstractMusinsaException(message, code, cause) {
}

