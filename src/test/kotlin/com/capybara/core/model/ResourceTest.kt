package com.capybara.core.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class ResourceTest {

    @Test
    fun `should define a resource name`() {
        val victim = object : Resource({
            name("test")
            methods {  }
            properties { }
        }) {}

        assertEquals("test", victim.name)
    }

    @Test
    fun `should define a resource methods`() {
        val victim = object : Resource({
            name("test")
            methods { get() }
            properties { }
        }) {}

        assertEquals(listOf(Get), victim.methods)
    }

    @Test
    fun `should define a resource properties`() {
        val victim = object : Resource({
            name("test")
            methods { get() }
            properties { property("prop", TextType) }
        }) {}

        assertEquals(listOf(Property("prop", TextType)), victim.properties)
    }
}