package com.example.myreminder.navigation

sealed class Page(val route: String) {
    data object Home : Page("home")
    data object Add : Page("add")
}