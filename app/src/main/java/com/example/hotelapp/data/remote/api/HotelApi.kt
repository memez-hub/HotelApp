package com.example.hotelapp.data.remote.api

import com.example.hotelapp.domain.remote.model.HotelResponse
import retrofit2.http.GET

interface HotelApi {

    @GET("hotels")
    suspend fun getHotels() : HotelResponse
}