package com.capybara.core.backend.data.mongodb

import com.capybara.core.backend.data.DataSource
import com.capybara.core.backend.data.ResourceBlob
import com.capybara.core.backend.data.ResourceIdentifier
import com.capybara.core.model.Resource
import com.mongodb.client.model.Filters.eq
import com.mongodb.client.result.UpdateResult
import com.mongodb.reactivestreams.client.MongoCollection
import io.reactivex.Observable
import io.reactivex.Single
import org.bson.Document
import org.bson.conversions.Bson

class MongoDBDatasource(private val collection: MongoCollection<Document>) : DataSource {

    override fun find(resource: Resource, identifier: ResourceIdentifier): Single<ResourceBlob> {
        val id = identifier.toString()

        return collection.rxFind(eq("id", id))
            .map { MongoDbResourceBlob(it) }
    }

    override fun delete(resource: Resource, identifier: ResourceIdentifier): Single<Unit> {
        val id = identifier.toString()
        return Single.fromPublisher(collection.deleteOne(eq("id", id))).map { Unit }
    }

    override fun deleteAll(resource: Resource, identifier: ResourceIdentifier): Single<Unit> {
        throw NotImplementedError()
    }

    override fun create(resource: Resource, data: ResourceBlob): Single<ResourceBlob> {
        val document = resource.properties
            .map { Pair(it.name, data.getValue(it) ) }
            .fold(Document()) { doc, values ->
                doc[values.first] = values.second
                doc
            }

        return Single.fromPublisher(collection.insertOne(document))
            .map { MongoDbResourceBlob(document) }
    }

    override fun update(resource: Resource, identifier: ResourceIdentifier, data: ResourceBlob): Single<ResourceBlob> {
        val id = identifier.toString()

        return collection.rxFind(eq("id", id))
            .map { document ->
                resource.properties
                    .map { prop -> Pair(prop.name, data.getValue(prop) ) }
                    .fold(document) { doc, values ->
                        doc[values.first] = values.second
                        doc
                    }
            }
            .flatMap { document -> collection.rxUpdateOne(eq("id", id), document).map { document } }
            .map { MongoDbResourceBlob(it) }
    }

    override fun findAll(resource: Resource): Observable<ResourceBlob> {
        return collection.rxFind().map { MongoDbResourceBlob(it) }
    }

    private fun MongoCollection<Document>.rxUpdateOne(filter: Bson, update: Bson): Single<UpdateResult> {
        return Single.fromPublisher(this.updateOne(filter, update))
    }

    private fun MongoCollection<Document>.rxFind(filter: Bson): Single<Document> {
        return Single.fromPublisher(this.find(filter))
    }

    private fun MongoCollection<Document>.rxFind(): Observable<Document> {
        return Observable.fromPublisher(this.find())
    }
}