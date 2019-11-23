package com.capybara.core.backend.data

import com.capybara.core.backend.data.mongodb.MongoDBDatasource
import com.capybara.core.model.Backend
import com.capybara.core.model.MongoDbBackend
import com.mongodb.ConnectionString
import com.mongodb.reactivestreams.client.MongoClients

object DataSourceFactory {

    fun create(backend: Backend): DataSource {
        return when(backend) {
            is MongoDbBackend -> createMongoDbDataSource(backend.collectionName, backend.database, backend.host, backend.port)
            else -> throw NotImplementedError(backend::class.toString())
        }
    }

    private fun createMongoDbDataSource(name: String, database: String, host: String, port: Int): MongoDBDatasource {
        val client = MongoClients.create(ConnectionString("mongodb://$host:$port"))
        val mongoDatabase = client.getDatabase(database)
        return MongoDBDatasource(mongoDatabase.getCollection(name))
    }
}