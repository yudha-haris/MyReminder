package com.example.myreminder.reminder.di

import androidx.room.Room
import com.example.myreminder.reminder.data.source.local.LocalDataSource
import com.example.myreminder.reminder.data.source.local.room.ReminderDatabase
import com.example.myreminder.reminder.data.source.remote.RemoteDataSource
import com.example.myreminder.reminder.data.source.remote.network.ApiService
import com.example.myreminder.reminder.domain.repository.IReminderRepository
import com.example.myreminder.core.utils.AppExecutors
import com.example.myreminder.reminder.data.Repository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory {
        get<ReminderDatabase>().reminderDao()
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            ReminderDatabase::class.java,
            "Reminder.db",
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single { AppExecutors() }
    single<IReminderRepository> {
        Repository(
            get(),
            get(),
            get()
        )
    }
}