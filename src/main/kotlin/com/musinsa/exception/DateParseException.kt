package com.musinsa.exception
import java.text.ParseException

class DateParseException(date: String, exception: ParseException?) :
    RuntimeException("$date is invalid date.", exception)
