package com.example.hotelapp.ui.mainScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.hotelapp.navigation.BottomNavItem
import com.example.hotelapp.navigation.HomeNavGraph
import com.example.hotelapp.navigation.SetupNavGraph
import com.example.hotelapp.ui.components.BottomNavigationBar
import com.example.hotelapp.ui.dashboard.components.DashboardScreenTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, content: @Composable ()-> Unit) {


    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Schedule,
        BottomNavItem.Search
    )

    val showBottomBar = items.any { it.route == currentRoute }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,

        topBar = {
            if(currentRoute != BottomNavItem.Search.route){
                DashboardScreenTopBar()
            }
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController
            )
        }
    ) { padding ->
        Box(Modifier.padding(padding)){
            content()
        }

    }
}
