package com.capybara.core.http

import com.capybara.core.backend.data.*
import com.capybara.core.model.Backend
import com.capybara.core.model.Get
import com.capybara.core.model.Method
import com.capybara.core.model.Resource
import io.reactivex.Single


class HttpHandler(
    private val resources: List<Resource>
) {
    fun handle(request: HttpRequest): Single<HttpResponse> {

        val resource = resources.single {
            request.path.contains(it.name)
        }
        val dataSource =  DataSourceFactory.create(resource.backend)

        return DatasourceDispatcher.dispatch(request,resource,dataSource)
    }
}