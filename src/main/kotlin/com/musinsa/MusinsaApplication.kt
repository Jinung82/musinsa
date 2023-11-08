package com.musinsa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScans


@SpringBootApplication
class MusinsaApplication

fun main(args: Array<String>) {
	runApplication<MusinsaApplication>(*args)
}
