package com.example.myreminder.pages.add

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myreminder.core.domain.repository.ReminderRepository
import com.example.myreminder.pages.home.utils.ReminderDummy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AddReminderViewModelTest {

    @Mock
    private lateinit var reminderRepository: ReminderRepository
    private lateinit var viewModel: AddReminderViewModel
    private val dummyReminder = ReminderDummy.generateReminderDummy()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = AddReminderViewModel(reminderRepository)
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
    fun `when add reminder Should call insert reminder repository`() = runTest {
        val reminder = dummyReminder[0]
        viewModel.addReminder(reminder)
        Mockito.verify(reminderRepository, times(1)).insertReminder(reminder)
    }
}

