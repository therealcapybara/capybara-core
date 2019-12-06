package com.capybara.core.http

import com.capybara.core.backend.data.ResourceBlob

class HttpRequest (
    val method: String,
    val path: String,
    val headers: Map<String, List<String>>,
    val body: ResourceBlob?
)
