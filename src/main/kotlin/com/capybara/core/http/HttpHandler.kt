package com.capybara.core.http

import com.capybara.core.backend.data.DataSourceFactory
import com.capybara.core.model.*
import io.reactivex.Single


class HttpHandler(
    private val resources: List<Resource>
) {
    fun handle(request: HttpRequest): Single<HttpResponse> {

        val resource = resources.single {
            request.path.contains(it.name)
        }

        val dataSource = DataSourceFactory.create(resource.backend)

        return when (val method = resource.methods.single { method -> request.method == method.name }) {
            is Get -> GetHttpHandler.handle(request, resource, dataSource)
            is Post -> PostHttpHandler.handle(request, resource, dataSource)
            is Put -> PutHttpHandler.handle(request, resource, dataSource)
            is Delete -> DeleteHttpHandler.handle(request, resource, dataSource)
            else -> throw NotImplementedError(method::class.toString())
        }
    }
}