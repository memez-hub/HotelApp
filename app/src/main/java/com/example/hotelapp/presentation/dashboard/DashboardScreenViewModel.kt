package com.example.hotelapp.presentation.dashboard

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

class DashboardScreenViewModel : ViewModel() {

    val hotelApi = RetrofitHelper.getInstance().create(HotelApi::class.java)


    var hotels by mutableStateOf<List<Hotel>>(emptyList())
        private set

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