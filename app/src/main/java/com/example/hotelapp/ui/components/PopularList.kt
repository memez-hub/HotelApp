package com.example.hotelapp.ui.components

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import com.example.hotelapp.model.Hotel

fun LazyListScope.popularList(
    populars: List<Hotel>,
    onCardClick: () -> Unit
) {
    items(populars) { item ->
        HotelCard(
            hotel = item,
            onCardClick = onCardClick
        )
    }
}