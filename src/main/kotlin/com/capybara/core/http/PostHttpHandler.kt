package com.capybara.core.http

import com.capybara.core.backend.data.DataSource
import com.capybara.core.backend.data.ResourceBlob
import com.capybara.core.model.Resource
import com.google.gson.Gson
import io.reactivex.Single

object PostHttpHandler {

    fun handle(request: HttpRequest, resource: Resource, datasource: DataSource): Single<HttpResponse> {
        //TODO: Validate resource path/uri
        val resourceBlob = request.body ?: throw IllegalArgumentException("")
        return datasource.create(resource, resourceBlob).map { toMap(it,resource) }.map { toJsonHttpResponse(it) }
    }

    private fun toMap(resourceBlob: ResourceBlob, resource: Resource): Map<String, Any> {
        return resource.properties
            .fold(mutableMapOf()) { map, property ->
                resourceBlob.getValue(property)?.let { map.put(property.name, it) }
                map
            }
    }

    private fun toJsonHttpResponse(response: Any): HttpResponse {
        val responseJsonBody = Gson().toJson(response, response::class.java)
        return HttpResponse(responseJsonBody, "OK")
    }
}