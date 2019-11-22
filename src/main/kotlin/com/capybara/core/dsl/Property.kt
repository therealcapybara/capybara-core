package com.capybara.core.dsl

class Property(val name: String) {
    private lateinit var type: Type

    fun hasType(type: Type) {
        this.type = type
    }
}