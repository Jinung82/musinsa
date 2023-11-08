package com.musinsa.main

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.transaction.annotation.EnableTransactionManagement
@ComponentScan(basePackages = ["com.musinsa"])
@EnableTransactionManagement
@SpringBootTest
class MusinsaApplicationTests {

	@Test
	fun contextLoads() {
	}

}
