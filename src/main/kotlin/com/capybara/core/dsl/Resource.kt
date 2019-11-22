package com.capybara.core.dsl

class ResourceMethods: ArrayList<Method>()

fun Resource.methods(body: ResourceMethods.() -> Unit): ResourceMethods {
    val methods = ResourceMethods()
    methods.body()
    this.methods = methods
    return methods
}

fun ResourceMethods.allows(method: Method) {
    this.add(method)
}

fun Resource.property(name: String, body: Property.() -> Unit = { }): Property {
    val property = Property(name)
    property.body()
    properties.add(Property(name))
    return property
}

class Resource(
    val name: String
) {
    lateinit var properties: MutableList<Property>
    lateinit var methods: ResourceMethods
}

fun resource(name: String, body: Resource.() -> Unit): Resource {
    val resource = Resource(name)
    resource.body()
    return resource
}