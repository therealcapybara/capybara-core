package com.capybara.core.backend.data

import com.capybara.core.http.HttpRequest
import com.capybara.core.http.HttpResponse
import com.capybara.core.model.Get
import com.capybara.core.model.Post
import com.capybara.core.model.Resource
import com.google.gson.Gson
import io.reactivex.Single

object DatasourceDispatcher {

    fun dispatch(request: HttpRequest, resource: Resource, datasource: DataSource): Single<HttpResponse> {

        val method = resource.methods.first { method -> request.method == method.name }
        return when (method) {
            is Get -> dispatchGet(request, resource, datasource)
            else -> throw NotImplementedError(method::class.toString())
        }
    }

    fun dispatchGet(request: HttpRequest, resource: Resource, datasource: DataSource): Single<HttpResponse> {
        val pathVariables = request.path.split("/")
        val isSingleResourceRequest = pathVariables.size > 1
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

    fun toMap(resourceBlob: ResourceBlob, resource: Resource): Map<String, Any>{
        return resource.properties
            .fold(mutableMapOf<String, Any>()) { map, property ->
                resourceBlob.getValue(property)?.let { map.put(property.name, it) }
                map
            }
    }

    private fun toJsonHttpResponse( response: Any): HttpResponse{
        val response = Gson().toJson(response, response::class.java)

        return HttpResponse(response, "OK")
    }



}
