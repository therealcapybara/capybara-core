package com.capybara.core.http

import com.capybara.core.model.Resource

class HttpResponse (
    val body: Resource,
    val status: String
)