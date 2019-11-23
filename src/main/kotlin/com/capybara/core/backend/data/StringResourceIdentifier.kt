package com.capybara.core.backend.data

class StringResourceIdentifier(private val value: String) : ResourceIdentifier{

    override fun toString(): String {
        return value
    }

}