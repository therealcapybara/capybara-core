package com.capybara.core.http

import com.capybara.core.model.Resource

class HttpRequest (
    val method: String,
    val path: String,
    val headers: Map<String, List<String>>,
    val body: String?
)
