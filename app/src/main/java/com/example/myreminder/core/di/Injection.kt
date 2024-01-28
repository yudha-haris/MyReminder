package com.example.myreminder.core.di

import android.content.Context
import com.example.myreminder.core.data.ReminderRepository
import com.example.myreminder.core.data.source.local.LocalDataSource
import com.example.myreminder.core.data.source.local.room.ReminderDatabase
import com.example.myreminder.core.data.source.remote.RemoteDataSource
import com.example.myreminder.core.data.source.remote.network.ApiConfig
import com.example.myreminder.core.domain.usecase.ReminderInteractor
import com.example.myreminder.core.domain.usecase.ReminderUseCase
import com.example.myreminder.core.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context): ReminderRepository {
        val database = ReminderDatabase.getInstance(context)

        val apiService = ApiConfig.provideApiService()
        val remoteDataSource = RemoteDataSource.getInstance(apiService)
        val localDataSource = LocalDataSource.getInstance(database.reminderDao())
        val appExecutors = AppExecutors()

        return ReminderRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideReminderUseCase(context: Context): ReminderUseCase {
        val repository = provideRepository(context)
        return ReminderInteractor(repository)
    }
}