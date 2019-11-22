package com.capybara.core.backend.datasource

interface Datasource<TKey, T> {
    fun findById()
    fun findAll()
    fun delete()
    fun save()
    fun saveAll()
}