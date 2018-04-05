package com.example.demo.controller

import com.example.demo.configure.FooProperties
import org.springframework.core.env.Environment
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * this code is investigation.
 */
@RestController
@RequestMapping(path = ["hello"], produces = [MediaType.TEXT_PLAIN_VALUE])
class HelloController(
        private val foo: FooProperties,
        private val env: Environment) {

    @GetMapping(path = ["world"])
    fun greeting(): String {
        println(foo)

        env.getProperty("my-app.my-module.foo.first-name")?.let { println(it) }
        env.getProperty("java_home")?.let { println(it) }

        return "hello world"
    }

}