package com.capybara.core.backend.data.mongodb

import com.capybara.core.backend.data.ResourceIdentifier
import java.util.*

class MongoResourceIdentifier(private val id: UUID) : ResourceIdentifier {
    override fun toString() = id.toString()
}

fun newMongoResourceIdentifier(): ResourceIdentifier = MongoResourceIdentifier(UUID.randomUUID())