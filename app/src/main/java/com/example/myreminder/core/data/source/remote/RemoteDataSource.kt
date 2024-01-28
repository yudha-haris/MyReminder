package com.example.myreminder.core.data.source.remote

import com.example.myreminder.core.data.source.remote.network.ApiResponse
import com.example.myreminder.core.data.source.remote.network.ApiService
import com.example.myreminder.core.data.source.remote.response.TodosItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource private constructor(private val apiService: ApiService) {

    companion object {
        @Volatile
        private var instace: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instace ?: synchronized(this) {
                instace ?: RemoteDataSource(service)
            }
    }

    fun getAllReminder(): Flow<ApiResponse<List<TodosItem>>> {
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.todos
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.todos))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }

        }.flowOn(Dispatchers.IO)
    }
}