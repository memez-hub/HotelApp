package com.example.hotelapp.presentation.search

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelapp.data.mapper.toHotel
import com.example.hotelapp.data.remote.api.HotelApi
import com.example.hotelapp.data.remote.api.RetrofitHelper
import com.example.hotelapp.domain.local.model.Hotel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchScreenViewModel : ViewModel() {


    val hotelApi = RetrofitHelper.getInstance().create(HotelApi::class.java)

    var hotels by mutableStateOf<List<Hotel>>(emptyList())
        private set


    var searchQuery by mutableStateOf("")
        private set

    val filteredHotels: List<Hotel>
        get() {
            if (searchQuery.isBlank()) return hotels
            return hotels.filter {
                it.name.contains(searchQuery, ignoreCase = true)
            }
        }

    fun onSearchQueryChange(query: String) {
        searchQuery = query
    }

    fun loadHotels() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    hotelApi.getHotels()
                }
                hotels = response.map { it.toHotel() }
                Log.d("tag", "Hotels fetched")
            } catch (e: Exception) {
                Log.e("tag", "Error fetching hotels", e)
            }
        }
    }
}
