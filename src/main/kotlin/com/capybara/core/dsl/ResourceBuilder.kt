package com.capybara.core.dsl

import com.capybara.core.model.*

class ResourceBuilder {
    lateinit var name: String
    lateinit var properties: MutableList<Property>
    lateinit var methods: MutableList<Method>
    lateinit var backend: Backend

    fun name(name: String) {
        this.name = name
    }

    fun methods(body: MethodsBuilder.() -> Unit) {
        methods = mutableListOf()
        MethodsBuilder(this).body()
    }

    fun properties(body: PropertiesBuilder.() -> Unit) {
        properties = mutableListOf()
        PropertiesBuilder(this).body()
    }

    fun backend(body: BackendBuilder.() -> Unit) {
        BackendBuilder(this).body()
    }
}

class MethodsBuilder(private val resourceBuilder: ResourceBuilder) {
    fun get() {
        this.resourceBuilder.methods.add(Get)
    }

    fun post() {
        this.resourceBuilder.methods.add(Post)
    }

    fun delete() {
        this.resourceBuilder.methods.add(Delete)
    }

    fun put() {
        this.resourceBuilder.methods.add(Put)
    }
}

class PropertiesBuilder(private val resourceBuilder: ResourceBuilder) {
    fun property(name: String, value: PropertyType) {
        resourceBuilder.properties.add(Property(name, value))
    }
}

class BackendBuilder(private val resourceBuilder: ResourceBuilder) {
    fun mongoDb(collection: String = resourceBuilder.name, database: String, host: String, port: Int) {
        resourceBuilder.backend = MongoDbBackend(collection,  database, host, port)
    }
}