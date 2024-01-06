package com.example.myreminder.core.domain.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.myreminder.core.data.source.local.LocalDataSource
import com.example.myreminder.core.data.source.local.entity.ReminderEntity
import com.example.myreminder.core.data.source.remote.RemoteDataSource
import com.example.myreminder.core.data.source.remote.network.Result
import com.example.myreminder.core.domain.mapper.ReminderMapper
import com.example.myreminder.core.domain.model.Reminder
import com.example.myreminder.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow

class ReminderRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) {
    companion object {
        @Volatile
        private var instance: ReminderRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): ReminderRepository = instance ?: synchronized(this) {
            instance ?: ReminderRepository(remoteDataSource, localDataSource, appExecutors)
        }
    }

    suspend fun fetchReminder() {
        try {
            val response = remoteDataSource.getAllReminder();
            val domain = ReminderMapper.mapResponseToDomain(response)
            val entity = ReminderMapper.mapDomainToEntities(domain)
            appExecutors.diskIO().execute {
                localDataSource.insertAllReminder(entity)
            }
        } catch (e: Exception) {
            Log.e("ReminderRepository", e.message.toString())
        }
    }

    fun insertReminder(reminder: Reminder) {
        val entity = ReminderMapper.mapDomainToEntity(reminder)
        appExecutors.diskIO().execute {
            localDataSource.insertReminder(entity)
        }

    }

    fun getAllReminder(): LiveData<List<Reminder>> {
        return localDataSource.getAllReminder().map {
            ReminderMapper.mapEntitiesToDomain(it)
        }
    }

}