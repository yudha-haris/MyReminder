package com.example.myreminder.reminder

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myreminder.reminder.presentation.navigation.Page
import com.example.myreminder.reminder.presentation.pages.add.AddReminderPage
import com.example.myreminder.reminder.presentation.pages.add.AddReminderViewModel
import com.example.myreminder.reminder.presentation.pages.home.HomePage
import com.example.myreminder.reminder.presentation.pages.home.HomeViewModel

@Composable
fun ReminderApp(
    homeViewModel: HomeViewModel,
    addReminderViewModel: AddReminderViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Page.Home.route) {
        composable(Page.Home.route) {
            HomePage(viewModel = homeViewModel, navController, modifier)
        }
        composable(Page.Add.route) {
            AddReminderPage(
                viewModel = addReminderViewModel,
                navController = navController,
                modifier = modifier
            )
        }
    }

}