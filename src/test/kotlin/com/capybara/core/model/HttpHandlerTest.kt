package com.capybara.core.model

import com.capybara.core.http.HttpHandler
import com.capybara.core.http.HttpRequest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class HttpHandlerTest {

    @Test
    fun `should fetch all projects`() {
        val victim = object : Resource({
            name("project")
            methods { get() }
            properties {
                property("title", TextType)
                property("abstract", TextType)
            }
            backend {
                mongoDb(host = "localhost", port = 27017,  database = "xpto")
            }
        }) {}

        Assertions.assertEquals(MongoDbBackend("project", "xpto", "localhost", 27017), victim.backend)

        val httpHandler = HttpHandler(listOf(victim))


        val response = httpHandler.handle(HttpRequest("GET", "/project", emptyMap(),null)).blockingGet()

        Assertions.assertNotNull(response)
    }
}