package com.example.hotelapp.navigation


const val HOME_GRAPH_ROUTE = "home"
const val HOTEL_GRAPH_ROUTE = "hotel"
const val ROOT_GRAPH_ROUTE = "root"

sealed class Screen(val route: String) {
    object Home: Screen( route = "home_screen")
    object Schedule: Screen(route = "schedule_screen")
    object Details: Screen(route = "details_screen?id={id}"){
        fun passId(id: Int = 0): String{
            return "detail_screen?id=$id"
        }
    }
    object Search: Screen (route = "search_screen")
}