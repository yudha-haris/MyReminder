package com.example.myreminder.core.data.source.remote.network

import com.example.myreminder.core.data.source.remote.response.ReminderResponse
import retrofit2.http.GET

interface ApiService {
    @GET("b637a5c9-2181-46e9-9a07-d770dcaf17d4")
    suspend fun getList(): ReminderResponse
}