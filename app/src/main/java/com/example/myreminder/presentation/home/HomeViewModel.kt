package com.example.myreminder.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.myreminder.core.data.source.Resource
import com.example.myreminder.core.domain.model.Reminder
import com.example.myreminder.core.domain.usecase.ReminderUseCase

class HomeViewModel(private val reminderUseCase: ReminderUseCase) : ViewModel() {

    init {
        getReminder()
    }
    
    fun getReminder(): LiveData<Resource<List<Reminder>>> = reminderUseCase.getAllReminder().asLiveData()
}