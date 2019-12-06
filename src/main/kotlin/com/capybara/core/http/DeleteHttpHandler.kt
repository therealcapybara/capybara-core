package com.capybara.core.http

import com.capybara.core.backend.data.DataSource
import com.capybara.core.backend.data.StringResourceIdentifier
import com.capybara.core.model.Resource
import io.reactivex.Single

object DeleteHttpHandler {

    fun handle(request: HttpRequest, resource: Resource, datasource: DataSource): Single<HttpResponse> {
        val pathVariables = request.path.split("/")
        return datasource.delete(resource, StringResourceIdentifier(pathVariables.last()))
            .map { HttpResponse("", "No Content") }
    }
}