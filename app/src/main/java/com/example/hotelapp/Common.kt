package com.example.hotelapp

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.hotelapp.navigation.Route

fun NavBackStackEntry.getHotelId(
    navController: NavController
): String {
    return navController
        .getBackStackEntry(Route.DetailGraph.route)
        .arguments!!
        .getString("hotelId")!!
}
