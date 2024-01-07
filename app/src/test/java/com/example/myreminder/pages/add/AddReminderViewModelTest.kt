package com.example.myreminder.pages.add

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner
import java.util.Date

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
        val id = Date().time.toInt()
        viewModel.addReminder(id, reminder.title, reminder.description, "2023-10-11", "12:22")

        Mockito.verify(reminderRepository, times(1)).insertReminder(Reminder(id, reminder.title, reminder.description, "2023-10-11 12:22"))
    }

    @Test
    fun `when add incomplete reminder Shouldn't call insert reminder repository`() = runTest {
        val reminder = dummyReminder[0]
        val id = Date().time.toInt()
        viewModel.addReminder(id, reminder.title, reminder.description, "2023-10-11", "Pilih Waktu")
        Mockito.verify(reminderRepository, times(0)).insertReminder(reminder)
    }
}

