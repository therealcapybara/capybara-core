package com.capybara.core.backend.data

import com.capybara.core.model.Resource
import io.reactivex.Observable
import io.reactivex.Single

interface DataSource {
    fun find(resource: Resource, identifier: ResourceIdentifier) : Single<ResourceBlob>

    fun delete(resource: Resource, identifier: ResourceIdentifier): Single<Unit>

    fun deleteAll(resource: Resource, identifier: ResourceIdentifier): Single<Unit>

    fun create(resource: Resource,  data: ResourceBlob): Single<ResourceBlob>

    fun update(resource: Resource, identifier: ResourceIdentifier, data: ResourceBlob): Single<ResourceBlob>

    fun findAll(resource: Resource): Observable<ResourceBlob>
}