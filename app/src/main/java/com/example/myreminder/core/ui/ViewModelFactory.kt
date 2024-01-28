package com.example.myreminder.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myreminder.core.di.Injection
import com.example.myreminder.core.domain.usecase.ReminderUseCase
import com.example.myreminder.presentation.add.AddReminderViewModel
import com.example.myreminder.presentation.home.HomeViewModel

class ViewModelFactory private constructor(private val reminderUseCase: ReminderUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance
                ?: synchronized(this) {
                    instance
                        ?: ViewModelFactory(
                            Injection.provideReminderUseCase(
                                context
                            )
                        )
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(reminderUseCase) as T
            }
            modelClass.isAssignableFrom(AddReminderViewModel::class.java) -> {
                AddReminderViewModel(reminderUseCase) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}