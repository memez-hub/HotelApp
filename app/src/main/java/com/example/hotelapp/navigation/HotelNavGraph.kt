package com.example.hotelapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.hotelapp.getHotelId
import com.example.hotelapp.presentation.detail.DetailScreen


fun NavGraphBuilder.hotelNavGraph(navController: NavHostController) {
    navigation(
        startDestination = Route.HotelDetail.route,
        route = Route.DetailGraph.route

    ){
        composable(route = Route.HotelDetail.route){
            val hotelId = it.getHotelId(navController)
            DetailScreen(navController = navController , hotelId = hotelId)
        }
    }
}

const val DETAIL_ARGUMENT_KEY = "id"
sealed class HotelScreen(val route:String){
    object Details : HotelScreen(route = "details_screen?id={id}"){
        fun passID(id : Int = 1): String{
            return "details_screen?id=$id"
        }
    }
}