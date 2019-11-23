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
            backend { mongoDb(host = "localhost", port = 27017, database = "sdgv") }
        }) {}

        assertEquals("test", victim.name)
    }

    @Test
    fun `should define a resource methods`() {
        val victim = object : Resource({
            name("test")
            methods { get() }
            properties { }
            backend { mongoDb(host = "localhost", port = 27017, database = "xpto") }
        }) {}

        assertEquals(listOf(Get), victim.methods)
    }

    @Test
    fun `should define a resource properties`() {
        val victim = object : Resource({
            name("test")
            methods { get() }
            properties { property("prop", TextType) }
            backend { mongoDb(host = "localhost", port = 27017,  database = "xpto") }
        }) {}

        assertEquals(listOf(Property("prop", TextType)), victim.properties)
    }

    @Test
    fun `should define a resource backend as mongodb`() {
        val victim = object : Resource({
            name("test")
            methods { get() }
            properties { property("prop", TextType) }
            backend {
                mongoDb(host = "localhost", port = 27017,  database = "xpto")
            }
        }) {}

        assertEquals(MongoDbBackend("test", "xpto", "localhost", 27017), victim.backend)
    }

}