package com.ardidong.moviesapp.ui.navigation

sealed class  MainDestination(val route: String) {
    object Home : MainDestination("home")

    object Detail: MainDestination("detail/{movieId}")

    object Review: MainDestination("review/{movieId}")
}