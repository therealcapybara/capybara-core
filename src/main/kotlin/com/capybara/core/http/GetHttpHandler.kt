package com.capybara.core.http

import com.capybara.core.backend.data.*
import com.capybara.core.model.Resource
import com.google.gson.Gson
import io.reactivex.Single

object GetHttpHandler {

    fun handle(request: HttpRequest, resource: Resource, datasource: DataSource): Single<HttpResponse> {
        val pathVariables = request.path.split("/")
        val isSingleResourceRequest = pathVariables.size - 1 > 1
        return if (isSingleResourceRequest) {
            datasource
                .find(resource, StringResourceIdentifier(pathVariables.last()))
                .map { toMap(it, resource) }
                .map { toJsonHttpResponse(it) }
        } else {
            datasource
                .findAll(resource)
                .map { toMap(it, resource) }
                .toList()
                .map { toJsonHttpResponse(it) }
        }
    }

    private fun toMap(resourceBlob: ResourceBlob, resource: Resource): Map<String, Any> {
        return resource.properties
            .fold(mutableMapOf<String, Any>()) { map, property ->
                resourceBlob.getValue(property)?.let { map.put(property.name, it) }
                map
            }
    }

    private fun toJsonHttpResponse(response: Any): HttpResponse {
        val response = Gson().toJson(response, response::class.java)

        return HttpResponse(response, "OK")
    }
}
