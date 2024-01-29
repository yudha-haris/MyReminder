package com.example.myreminder.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.myreminder.core.data.source.Resource
import com.example.myreminder.core.domain.model.Reminder
import com.example.myreminder.core.domain.usecase.ReminderUseCase
import com.example.myreminder.core.utils.DateUtils
import kotlinx.coroutines.flow.map

class HomeViewModel(private val reminderUseCase: ReminderUseCase) : ViewModel() {

    init {
        getReminder()
    }
    
    fun getReminder(): LiveData<Resource<List<Reminder>>> {
        return reminderUseCase.getAllReminder().map {
            when(it) {
                is Resource.Success -> {
                    val sorted = it.data ?: emptyList()
                    val result = ArrayList<Reminder>()

                    val active = ArrayList<Reminder>()
                    val inactive = ArrayList<Reminder>()
                    sorted.forEach { item ->
                        val isPast = DateUtils.isPast(item.dateTime)
                        if (isPast) {
                            inactive.add(item)
                        } else {
                            active.add(item)
                        }
                    }
                    result.addAll(active)
                    result.addAll(inactive)
                    Resource.Success<List<Reminder>>(result)
                }
                is Resource.Loading -> {
                    Resource.Loading()
                }
                is Resource.Error -> {
                    Resource.Error(it.message.toString())
                }
            }
        }.asLiveData()
    }
}