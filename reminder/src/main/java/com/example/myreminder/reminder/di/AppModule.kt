package com.example.myreminder.reminder.di

import com.example.myreminder.reminder.domain.usecase.ReminderInteractor
import com.example.myreminder.reminder.domain.usecase.ReminderUseCase
import com.example.myreminder.reminder.presentation.pages.add.AddReminderViewModel
import com.example.myreminder.reminder.presentation.pages.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<ReminderUseCase> { ReminderInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { AddReminderViewModel(get()) }
}