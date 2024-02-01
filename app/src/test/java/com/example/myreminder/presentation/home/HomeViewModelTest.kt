package com.example.myreminder.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.myreminder.core.data.source.Resource
import com.example.myreminder.core.domain.model.Reminder
import com.example.myreminder.core.domain.usecase.ReminderUseCase
import com.example.myreminder.presentation.home.HomeViewModel
import com.example.myreminder.utils.ReminderDummy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @Mock
    private lateinit var reminderUseCase: ReminderUseCase
    private lateinit var homeViewModel: HomeViewModel
    private val dummyReminder = ReminderDummy.generateReminderDummy()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        val expectedUseCase: Flow<Resource<List<Reminder>>> =
            flowOf(Resource.Success(dummyReminder))
        `when`(reminderUseCase.getAllReminder()).thenReturn(expectedUseCase)

        homeViewModel = HomeViewModel(reminderUseCase)
    }

    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setupDispatcher() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDownDispatcher() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when Get Reminder Should Not Null and Return Success`() = runTest {
        val observer = Observer<Resource<List<Reminder>>> {}

        try {
            val actualReminder = homeViewModel.getReminder().observeForever(observer)
            Mockito.verify(reminderUseCase, times(2)).getAllReminder()

            assertNotNull(actualReminder)

        } finally {
            homeViewModel.getReminder().removeObserver(observer)
        }
    }

}