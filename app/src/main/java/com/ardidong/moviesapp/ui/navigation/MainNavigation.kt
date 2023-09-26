package com.ardidong.moviesapp.ui.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ardidong.domain.common.extension.orMin
import com.ardidong.moviesapp.ui.detail.DetailScreen
import com.ardidong.moviesapp.ui.detail.ReviewScreen
import com.ardidong.moviesapp.ui.home.HomeScreen

@Composable
fun MainNavigation(modifier: Modifier = Modifier, navController: NavHostController = rememberNavController()) {
    NavHost(modifier = modifier, navController = navController, startDestination = MainDestination.Home.route){
        animatedComposable(
            route = MainDestination.Home.route
        ){
            HomeScreen(navController = navController)
        }

        animatedComposable(
            route = MainDestination.Detail.route,
            arguments = listOf(navArgument("movieId") {type = NavType.IntType})
        ){ backStackEntry ->
            DetailScreen(navController = navController, movieId = backStackEntry.arguments?.getInt("movieId").orMin())
        }

        animatedComposable(
            route = MainDestination.Review.route,
            arguments = listOf(navArgument("movieId") {type = NavType.IntType})
        ){ navBackStackEntry ->
            ReviewScreen(navController = navController, movieId = navBackStackEntry.arguments?.getInt("movieId").orMin())
        }
    }
}


fun NavGraphBuilder.animatedComposable(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        arguments = arguments,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                animationSpec = tween(700)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                animationSpec = tween(700)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                animationSpec = tween(700)
            )
        }
    ){ navBackStackEntry ->
        content(navBackStackEntry)
    }
}