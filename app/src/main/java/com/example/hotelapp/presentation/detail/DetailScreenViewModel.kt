package com.example.hotelapp.presentation.detail

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

class DetailScreenViewModel: ViewModel() {

    val hotelApi = RetrofitHelper.getInstance().create(HotelApi::class.java)


    var hotel by mutableStateOf<Hotel?>(null)
        private set

    fun loadHotelById(hotelId : Int){
        viewModelScope.launch{
            try {
                val response = withContext(Dispatchers.IO){
                    hotelApi.getHotelsById(hotelId)
                }
                hotel = response.toHotel()
            }
            catch (e: Exception){
                Log.e("tag", "Error fetching hotel $hotelId", e)
            }
        }
    }

}