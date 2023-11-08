package com.musinsa.main

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@SpringBootTest(classes = [MusinsaApplicationTests::class])
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
abstract class SpringSupportTest()
