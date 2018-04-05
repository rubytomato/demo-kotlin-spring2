package com.example.demo.configure

import com.example.demo.type.Color
import com.example.demo.type.Part
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.DeprecatedConfigurationProperty
import org.springframework.stereotype.Component

/**
 * Foo Properties description.
 *
 * this code is investigation.
 */
@Component
@ConfigurationProperties(prefix = "my-app.my-module.foo")
class FooProperties {

    /**
     * foo name.
     */
    lateinit var name: String

    /**
     * foo max height.
     */
    var maxHeight: Int = 0

    /**
     * foo min height.
     */
    var minHeight: Int = 0

    /**
     * foo color description.
     */
    lateinit var color: Color

    /**
     * foo colors description.
     */
    var colors: MutableList<Color> = mutableListOf()

    /**
     * foo parts description.
     */
    var parts: MutableMap<Part, String> = mutableMapOf()

    /**
     * foo budget description.
     */
    @get:DeprecatedConfigurationProperty(reason = "reason not to use budget.")
    @Deprecated("reason not to use budget.")
    var budget: Int = 0

    override fun toString(): String {
        return "FooProperties(name='$name', maxHeight=$maxHeight, minHeight=$minHeight, color=$color, colors=$colors, parts=$parts, budget=$budget)"
    }

}