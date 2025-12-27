package com.example.hotelapp.ui.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.hotelapp.R
import com.example.hotelapp.model.Hotel

class SearchScreenViewModel : ViewModel() {

    private val _hotels = listOf(
        Hotel(
            1, "Grand Palace Hotel", "Paris", "4.5",
            rating = "rgr",
            imageRes = R.drawable.hotelimage
        ),
        Hotel(
            1, "Mini place", "Paris", "4.5",
            rating = "rgr",
            imageRes = R.drawable.hotelimage
        ),
        Hotel(
            1, "Just Appartment", "Paris", "4.5",
            rating = "rgr",
            imageRes = R.drawable.hotelimage
        ),
        Hotel(
            1, "Tower", "Paris", "4.5",
            rating = "rgr",
            imageRes = R.drawable.hotelimage
        ),
        Hotel(
            1, "Beach house", "Paris", "4.5",
            rating = "rgr",
            imageRes = R.drawable.hotelimage
        ),
    )

    var searchQuery by mutableStateOf("")
        private set

    val filteredHotels: List<Hotel>
        get() {
            if (searchQuery.isBlank()) return _hotels
            return _hotels.filter {
                it.name.contains(searchQuery, ignoreCase = true)
            }
        }

    fun onSearchQueryChange(query: String) {
        searchQuery = query
    }
}
