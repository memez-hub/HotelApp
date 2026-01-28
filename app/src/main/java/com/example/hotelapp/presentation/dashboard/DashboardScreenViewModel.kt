package com.example.hotelapp.presentation.dashboard

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelapp.data.remote.api.HotelApi
import com.example.hotelapp.data.remote.api.RetrofitHelper
import com.example.hotelapp.domain.remote.model.HotelResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class DashboardScreenViewModel : ViewModel() {

    val hotelApi = RetrofitHelper.getInstance().create(HotelApi::class.java)

    private suspend fun fetchHotels(): HotelResponse {
        return hotelApi.getHotels()
    }

    var hotels by mutableStateOf<HotelResponse?>(null)
        private set

    fun loadHotels() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    hotelApi.getHotels()
                }
                hotels = response
                Log.d("tag", "Hotels fetched")
            } catch (e: Exception) {
                Log.e("tag", "Error fetching hotels", e)
            }
        }
    }
}