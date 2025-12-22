package com.example.hotelapp.ui.dashboard

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotelapp.R
import com.example.hotelapp.model.Filters
import com.example.hotelapp.model.Hotel
import com.example.hotelapp.ui.components.FilterCard
import com.example.hotelapp.ui.components.HotelCard
import com.example.hotelapp.ui.components.popularList


@Composable
fun DashboardScreen(
    onHotelCardClick: () -> Unit,
    paddingValues: PaddingValues
) {

    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        item {
            FilterSection(
                modifier = Modifier.fillMaxWidth(),
                filterList = filterList,
                onFilterClicked = {},
            )
        }
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 16.dp, start = 24.dp, end = 24.dp, top = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    "Near Location",
                    color = Color(0xFF0F0F0F),
                    fontSize = 16.sp,
                )
                Text(
                    "See all",
                    color = Color(0xFF4C4DDC),
                    fontSize = 14.sp,
                )
            }
        }
        item {
            HotelSection(
                hotelList = hotelList,
                onRedHeartClick = {
                    Toast.makeText(context, "Heart Click", Toast.LENGTH_SHORT).show()
                },
                onHotelCardClick = { onHotelCardClick() }
            )
        }
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    "Popular Hotel",
                    color = Color(0xFF0F0F0F),
                    fontSize = 16.sp,
                )
                Text(
                    "See all",
                    color = Color(0xFF4C4DDC),
                    fontSize = 14.sp,
                )
            }
        }
        popularList(
            populars = hotelList,
            onCardClick = { onHotelCardClick() }
        )

    }
}

@Composable
private fun FilterSection(
    modifier: Modifier,
    filterList: List<Filters>,
    onFilterClicked: () -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
    ) {
        items(filterList) { filter ->
            FilterCard(
                filterName = filter.filterName,
                filterIcon = filter.filterIcon,
                onClick = onFilterClicked,
                isActive = false
            )
        }
    }

}

@Composable
private fun HotelSection(
    hotelList: List<Hotel>,
    onRedHeartClick: () -> Unit,
    onHotelCardClick: () -> Unit
) {
    LazyRow(modifier = Modifier.padding(end = 12.dp)) {
        items(hotelList) { hotel ->
            HotelCard(
                hotel = hotel,
                onFavoriteClick = onRedHeartClick,
                onHotelCardClick = onHotelCardClick

            )
        }
    }
}


val filterList = listOf(
    Filters(
        filterName = "Hotel",
        filterIcon = R.drawable.icon
    ),
    Filters(
        filterName = "Hotel",
        filterIcon = R.drawable.icon
    ),
    Filters(
        filterName = "Hotel",
        filterIcon = R.drawable.icon
    ),
    Filters(
        filterName = "Hotel",
        filterIcon = R.drawable.icon
    ),
    Filters(
        filterName = "Hotel",
        filterIcon = R.drawable.icon
    ),
    Filters(
        filterName = "Hotel",
        filterIcon = R.drawable.icon
    ),
    Filters(
        filterName = "Hotel",
        filterIcon = R.drawable.icon
    ),
    Filters(
        filterName = "Hotel",
        filterIcon = R.drawable.icon
    )
)

val hotelList = listOf(
    Hotel(
        name = "Hotel",
        location = "fefe",
        pricePerNight = "2332",
        rating = "323",
        imageRes = R.drawable.hotelimage
    ),
    Hotel(
        name = "Hotel",
        location = "fefe",
        pricePerNight = "2332",
        rating = "323",
        imageRes = R.drawable.hotelimage
    ),
    Hotel(
        name = "Hotel",
        location = "fefe",
        pricePerNight = "2332",
        rating = "323",
        imageRes = R.drawable.hotelimage
    ),
    Hotel(
        name = "Hotel",
        location = "fefe",
        pricePerNight = "2332",
        rating = "323",
        imageRes = R.drawable.hotelimage
    ), Hotel(
        name = "Hotel",
        location = "fefe",
        pricePerNight = "2332",
        rating = "323",
        imageRes = R.drawable.hotelimage
    ),
    Hotel(
        name = "Hotel",
        location = "fefe",
        pricePerNight = "2332",
        rating = "323",
        imageRes = R.drawable.hotelimage
    ),
    Hotel(
        name = "Hotel",
        location = "fefe",
        pricePerNight = "2332",
        rating = "323",
        imageRes = R.drawable.hotelimage
    )
)


