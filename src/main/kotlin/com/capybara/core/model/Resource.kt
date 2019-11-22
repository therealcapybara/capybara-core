package com.capybara.core.model

import com.capybara.core.dsl.ResourceBuilder

open class Resource(builderBody: ResourceBuilder.() -> Unit) {
    private val resourceBuilder = ResourceBuilder().apply { builderBody() }

    val name = resourceBuilder.name
    val methods = resourceBuilder.methods
    val properties = resourceBuilder.properties
}

abstract class Method(val name: String) {}

object Get : Method("GET"){}

object Post : Method("POST")

object Delete : Method("DELETE"){}

object Put : Method("PUT") {}

//open class Method
//
//object Get : Method()
//
//object Post : Method()
//
//object Put : Method()
//
//object Delete : Method()

fun validateProperties():Boolean{
    return true;
}

data class Property(
    val name: String,
    val type: PropertyType
)

open class PropertyType

object TextType : PropertyType()
