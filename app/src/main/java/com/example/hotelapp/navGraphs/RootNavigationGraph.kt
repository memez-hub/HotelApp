package com.example.hotelapp.navGraphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hotelapp.ui.detail.DetailScreen
import com.example.hotelapp.ui.mainScreen.MainScreen
import com.example.hotelapp.ui.theme.Gray

@Composable
fun RootNavigationGraph( navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.HOME
    ){
        composable(route = Graph.HOME){
            MainScreen(onHotelCardClick = {navController.navigate(Graph.BOOKING)})
        }
        composable(route = Graph.BOOKING){
            DetailScreen(
                onBackButtonClick = {navController.navigate(Graph.HOME)},
            )
        }

    }
}
object Graph {

    const val ROOT = "root_graph"
    const val HOME = "home_graph"
    const val BOOKING = "booking_graph"
}