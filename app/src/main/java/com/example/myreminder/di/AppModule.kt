package com.example.myreminder.di

import com.example.myreminder.core.domain.usecase.ReminderInteractor
import com.example.myreminder.core.domain.usecase.ReminderUseCase
import com.example.myreminder.presentation.add.AddReminderViewModel
import com.example.myreminder.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<ReminderUseCase> { ReminderInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { AddReminderViewModel(get()) }
}