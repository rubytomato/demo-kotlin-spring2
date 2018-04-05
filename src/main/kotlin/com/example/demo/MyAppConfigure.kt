package com.example.demo

import com.example.demo.configure.FooProperties
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

/**
 * this code is investigation.
 */
@Component
class MyAppConfigure {

    @Bean
    @ConditionalOnProperty(prefix = "my-app.my-module.foo", name = ["min-height"], havingValue = "100")
    fun bar(foo: FooProperties): BarProperties {
        println("inject : $foo")
        return BarProperties(foo.name)
    }

    data class BarProperties(val name: String)

}