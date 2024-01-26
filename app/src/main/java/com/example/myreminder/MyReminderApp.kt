package com.example.myreminder

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myreminder.pages.navigation.Page
import com.example.myreminder.pages.add.AddReminderPage
import com.example.myreminder.pages.add.AddReminderViewModel
import com.example.myreminder.pages.home.HomePage
import com.example.myreminder.pages.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
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