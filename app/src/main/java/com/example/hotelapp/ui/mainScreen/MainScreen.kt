package com.example.hotelapp.ui.mainScreen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.hotelapp.BottomNavItem
import com.example.hotelapp.navGraphs.HomeNavigationGraph
import com.example.hotelapp.ui.components.BottomNavigationBar
import com.example.hotelapp.ui.dashboard.components.DashboardScreenTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onHotelCardClick: () -> Unit) {
    val navController = rememberNavController()

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Schedule
    )

    val showBottomBar = items.any { it.route == currentRoute }

    Scaffold(

        topBar = {
            if(showBottomBar){
                DashboardScreenTopBar()
            }
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                items = items
            )
        }
    ) { padding ->
        HomeNavigationGraph(
            navController = navController,
            padding,
            onHotelCardClick = onHotelCardClick
        )

    }
}
