package com.example.myreminder.core.data

import com.example.myreminder.core.data.source.Resource
import com.example.myreminder.core.data.source.local.LocalDataSource
import com.example.myreminder.core.data.source.remote.RemoteDataSource
import com.example.myreminder.core.data.source.remote.network.ApiResponse
import com.example.myreminder.core.data.source.remote.response.TodosItem
import com.example.myreminder.core.domain.model.Reminder
import com.example.myreminder.core.domain.repository.IReminderRepository
import com.example.myreminder.core.utils.AppExecutors
import com.example.myreminder.core.utils.DateUtils
import com.example.myreminder.core.utils.ReminderMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ReminderRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IReminderRepository {

    override fun insertReminder(reminder: Reminder) {
        val entity = ReminderMapper.mapDomainToEntity(reminder)
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.insertReminder(entity)
        }
    }

    override fun getAllReminder(): Flow<Resource<List<Reminder>>> =
        object : com.example.myreminder.core.data.NetworkBoundResource<List<Reminder>, List<TodosItem>>() {
            override fun loadFromDB(): Flow<List<Reminder>> {
                return localDataSource.getAllReminder().map {
                    ReminderMapper.mapEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<TodosItem>>> {
                return remoteDataSource.getAllReminder()
            }

            override suspend fun saveCallResult(data: List<TodosItem>) {
                val domain = ReminderMapper.mapResponseToDomain(data)
                val sorted = domain.sortedBy { item ->
                    DateUtils.convertToDateTime(item.dateTime)
                }
                val entities = ReminderMapper.mapDomainToEntities(sorted)
                localDataSource.insertAllReminder(entities)
            }

            override fun shouldFetch(data: List<Reminder>?): Boolean {
                return true
            }
        }.asFlow()

}