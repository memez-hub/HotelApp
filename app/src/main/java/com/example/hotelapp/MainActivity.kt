package com.example.hotelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.hotelapp.domain.local.model.Hotel
import com.example.hotelapp.navigation.SetupNavGraph
import com.example.hotelapp.ui.theme.AppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val navController = rememberNavController()
                SetupNavGraph(navController)
            }
        }

    }
}

val hotelList = listOf(
    Hotel(
        id = 1,
        name = "Grand Palace Hotel",
        location = "Paris, France",
        pricePerNight = "343",
        rating = "4.5",
        imageRes = R.drawable.hotel330841_1280,
        accommodationType = "Hotels"
    ),
    Hotel(
        id = 2,
        name = "Sea View Resort",
        location = "Malibu, USA",
        pricePerNight = "412",
        rating = "4.7",
        imageRes = R.drawable.hotel461615_1280,
        accommodationType = "Resorts"
    ),
    Hotel(
        id = 3,
        name = "Mountain Inn",
        location = "Aspen, USA",
        pricePerNight = "299",
        rating = "4.3",
        imageRes = R.drawable.hotel2799682_1280,
        accommodationType = "Villas"
    ),
    Hotel(
        id = 4,
        name = "City Central Hotel",
        location = "London, UK",
        pricePerNight = "255",
        rating = "4.1",
        imageRes = R.drawable.building66789_1280,
        accommodationType = "Hotels"
    ),
    Hotel(
        id = 5,
        name = "Sunset Boulevard Hotel",
        location = "Los Angeles, USA",
        pricePerNight = "380",
        rating = "4.6",
        imageRes = R.drawable.grandhotel2178413_1280,
        "Hotels"
    ),
    Hotel(
        id = 6,
        name = "Riverside Lodge",
        location = "Zurich, Switzerland",
        pricePerNight = "330",
        rating = "4.4",
        imageRes = R.drawable.hotel2626098_1280,
        accommodationType = "Apartments"
    ),
    Hotel(
        id = 7,
        name = "The Royal Garden",
        location = "Tokyo, Japan",
        pricePerNight = "410",
        rating = "4.8",
        imageRes = R.drawable.mountainhotel1567013_1280,
        accommodationType = "Villas"
    ),
    Hotel(
        id = 8,
        name = "Harbour View Hotel",
        location = "Sydney, Australia",
        pricePerNight = "370",
        rating = "4.5",
        imageRes = R.drawable.lounge2930070_1280,
        accommodationType = "Hotels"
    ),
    Hotel(
        id = 9,
        name = "Alpine Retreat",
        location = "Chamonix, France",
        pricePerNight = "290",
        rating = "4.3",
        imageRes = R.drawable.building66789_1280,
        accommodationType = "Villas"
    ),
    Hotel(
        id = 10,
        name = "Desert Oasis Hotel",
        location = "Dubai, UAE",
        pricePerNight = "460",
        rating = "4.7",
        imageRes = R.drawable.hotel461615_1280,
        accommodationType = "Hotels"
    ),
    Hotel(
        id = 11,
        name = "The Vintage Inn",
        location = "Edinburgh, UK",
        pricePerNight = "270",
        rating = "4.2",
        imageRes = R.drawable.victoriafalls2646993_1280,
        accommodationType = "Apartments"
    ),
    Hotel(
        id = 12,
        name = "Ocean Breeze Resort",
        location = "Bali, Indonesia",
        pricePerNight = "340",
        rating = "4.6",
        imageRes = R.drawable.hotelimage,
        accommodationType = "Resorts"
    )
)




