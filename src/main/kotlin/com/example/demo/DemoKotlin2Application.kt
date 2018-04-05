package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

// same as @Configuration @EnableAutoConfiguration @ComponentScan
@SpringBootApplication
class DemoKotlin2Application

fun main(args: Array<String>) {
    runApplication<DemoKotlin2Application>(*args)
}
