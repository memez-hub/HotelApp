package com.example.hotelapp.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.hotelapp.hotelList
import com.example.hotelapp.domain.local.model.Hotel

class SearchScreenViewModel : ViewModel() {

    private val _hotels = hotelList

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
