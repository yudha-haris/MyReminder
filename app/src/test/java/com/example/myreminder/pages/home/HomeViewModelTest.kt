package com.example.myreminder.pages.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.myreminder.core.domain.model.Reminder
import com.example.myreminder.core.domain.repository.ReminderRepository
import com.example.myreminder.utils.ReminderDummy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
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
    private lateinit var reminderRepository: ReminderRepository
    private lateinit var homeViewModel: HomeViewModel
    private val dummyReminder = ReminderDummy.generateReminderDummy()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(reminderRepository)
    }

    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

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
        val observer = Observer<List<Reminder>> {}

        try {
            val expectedReminder = MutableLiveData<List<Reminder>>()

            expectedReminder.value = dummyReminder
            `when`(reminderRepository.getAllReminder()).thenReturn(expectedReminder)

            val actualReminder = homeViewModel.getReminder().observeForever(observer)
            Mockito.verify(reminderRepository).getAllReminder()

            Assert.assertNotNull(actualReminder)

        } finally {
            homeViewModel.getReminder().removeObserver(observer)
        }
    }

    @Test
    fun `when init Should call fetchReminder repository`() = runTest {
        Mockito.verify(reminderRepository, times(1)).fetchReminder()
    }

}