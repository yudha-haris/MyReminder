package com.example.myreminder

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myreminder.presentation.add.AddReminderPage
import com.example.myreminder.presentation.add.AddReminderViewModel
import com.example.myreminder.presentation.home.HomePage
import com.example.myreminder.presentation.home.HomeViewModel
import com.example.myreminder.navigation.Page

@Composable
fun MyReminderApp(
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