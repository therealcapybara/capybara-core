package com.capybara.core.http

import com.capybara.core.model.Resource


class HttpHandler(
    val resources: List<Resource>
) {
    fun handle(request: HttpRequest): HttpResponse {
        return resources.filter {
            request.path.contains(it.name)
        }.flatMap {
            it.methods.filter { method -> request.method == method.name }
        }.map {
            HttpResponse("body", "OK")
        }.first()

    }
}