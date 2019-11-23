package com.capybara.core.backend.data

import com.capybara.core.model.Property

interface ResourceBlob {
    fun getValue(property: Property): Any?
}