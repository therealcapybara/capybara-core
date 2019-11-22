package com.capybara.core.model

class Resource (
    val name: String,
    val methods: List<Method>,
    val properties: List<Property>
)

open class Method

object Get : Method()

object Post : Method()

object Put : Method()

object Delete : Method()

class Property(
    val name: String,
    val type: PropertyType
)

open class PropertyType

object TextType : PropertyType()