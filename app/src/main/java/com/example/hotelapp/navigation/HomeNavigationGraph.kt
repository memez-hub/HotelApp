package com.example.hotelapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hotelapp.ui.dashboard.DashboardScreen
import com.example.hotelapp.ui.schedule.ScheduleScreen
import com.example.hotelapp.ui.search.SearchScreen

@Composable
fun HomeNavGraph(
    navController: NavHostController,

    ) {
    NavHost(
        navController = navController,
        route = HOME_GRAPH_ROUTE,
        startDestination = BottomNavItem.Home.route
    ) {
        composable(
            route = BottomNavItem.Home.route,

            //transitions
            exitTransition = { NavTransitions.slideOutToLeft() },
            popEnterTransition = { NavTransitions.slideInFromLeft() }
        )
        {
            DashboardScreen(
                navController = navController
            )
        }
        composable(
            route = BottomNavItem.Schedule.route,
            exitTransition = { NavTransitions.slideOutToLeft() }

        ) {
            ScheduleScreen(navController = navController)
        }
        composable(route = BottomNavItem.Search.route) {
            SearchScreen( navController = navController
            )
        }
    }
}
