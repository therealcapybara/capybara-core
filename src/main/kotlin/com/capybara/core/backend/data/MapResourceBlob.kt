package com.capybara.core.backend.data

import com.capybara.core.model.Property

class MapResourceBlob(private val values: Map<String, Any>) : ResourceBlob {

    override fun getValue(property: Property): Any? {
        return values[property.name]
    }
}