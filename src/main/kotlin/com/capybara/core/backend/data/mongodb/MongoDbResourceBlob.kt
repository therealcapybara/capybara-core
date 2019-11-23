package com.capybara.core.backend.data.mongodb

import com.capybara.core.backend.data.ResourceBlob
import com.capybara.core.model.Property
import org.bson.Document

class MongoDbResourceBlob(private val document: Document) : ResourceBlob {

    override fun getValue(property: Property): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}