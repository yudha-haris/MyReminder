package com.example.myreminder.core.data.source.remote

import com.example.myreminder.core.data.source.remote.network.ApiService
import com.example.myreminder.core.data.source.remote.response.TodosItem

class RemoteDataSource private constructor(private val apiService: ApiService) {

    companion object {
        @Volatile
        private var instace: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instace ?: synchronized(this) {
                instace ?: RemoteDataSource(service)
            }
    }

    suspend fun getAllReminder(): List<TodosItem> {
        val response = apiService.getList();
        return response.todos
    }
}