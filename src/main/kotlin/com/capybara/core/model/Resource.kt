package com.capybara.core.model

import com.capybara.core.dsl.ResourceBuilder

open class Resource(builderBody: ResourceBuilder.() -> Unit) {
    private val resourceBuilder = ResourceBuilder().apply { builderBody() }

    val name = resourceBuilder.name
    val methods = resourceBuilder.methods
    val properties = resourceBuilder.properties
}

abstract class Method(val name: String) {

    //TODO change path to URI parameters
    abstract fun applyMethod(path: String, body: Resource?): Resource
}

object Get : Method("GET"){

    override fun applyMethod(path: String, body: Resource?): Resource {
        validateProperties()
        //TODO fetch from DB with parameters
        return Resource("funciona", emptyList(), emptyList())
    }
}

object Post : Method("POST"){
    override fun applyMethod(path: String, body: Resource?): Resource {
        validateProperties()
        TODO("not implemented")
        return Resource("funciona", emptyList(), emptyList())
    }

}

object Delete : Method("DELETE"){
    override fun applyMethod(path: String, body: Resource?): Resource {
        validateProperties()
        TODO("not implemented")
        return Resource("funciona", emptyList(), emptyList())
    }

}

object Put : Method("PUT") {
    override fun applyMethod(path: String, body: Resource?): Resource {
        validateProperties()
        TODO("not implemented")
        return Resource("funciona", emptyList(), emptyList())
    }

}

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
