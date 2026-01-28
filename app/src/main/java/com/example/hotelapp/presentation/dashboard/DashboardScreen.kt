package com.example.hotelapp.presentation.dashboard

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.hotelapp.R
import com.example.hotelapp.domain.local.model.Filters
import com.example.hotelapp.domain.local.model.Hotel
import com.example.hotelapp.hotelList
import com.example.hotelapp.navigation.Route
import com.example.hotelapp.presentation.components.FilterCard
import com.example.hotelapp.presentation.components.popularList


@Composable
fun DashboardScreen(
    viewModel: DashboardScreenViewModel = viewModel(),
    navController: NavController
) {
    val context = LocalContext.current

    var selectedFilterId by remember { mutableStateOf<Int?>(null) }

    LaunchedEffect(Unit) {
        viewModel.loadHotels()
    }

    val hotelList = if (viewModel.hotels.isNotEmpty()) viewModel.hotels else hotelList

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            FilterSection(
                filterList = filterList,
                selectedFilterId = selectedFilterId,
                onFilterClicked = { filterId ->
                    selectedFilterId = filterId
                },
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
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp,
                )
                Text(
                    "See all",
                    color = MaterialTheme.colorScheme.primary,
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
                onHotelCardClick = { hotelId ->
                    navController.navigate(
                        Route.DetailGraph.create(
                            hotelId
                        )
                    )
                }
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
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 16.sp,
                )
                Text(
                    "See all",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 14.sp,
                )
            }
        }
        popularList(
            populars = hotelList,
            onCardClick = { hotelId -> navController.navigate(Route.DetailGraph.create(hotelId)) }
        )

    }
}

@Composable
private fun FilterSection(
    filterList: List<Filters>,
    selectedFilterId: Int?,
    onFilterClicked: (Int) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
    ) {
        items(filterList) { filter ->
            FilterCard(
                filterName = filter.filterName,
                filterIcon = filter.filterIcon,
                isActive = filter.id == selectedFilterId,
                onClick = { onFilterClicked(filter.id) },
            )
        }
    }

}

@Composable
private fun HotelSection(
    hotelList: List<Hotel>,
    onRedHeartClick: () -> Unit,
    onHotelCardClick: (String) -> Unit
) {
    LazyRow(modifier = Modifier.padding(end = 12.dp)) {
        items(hotelList) { hotel ->
            CaruselHotelCard(
                hotel = hotel,
                onFavoriteClick = onRedHeartClick,
                onHotelCardClick = { onHotelCardClick(hotel.id.toString()) }

            )
        }
    }
}

@Composable
private fun CaruselHotelCard(
    modifier: Modifier = Modifier,
    hotel: Hotel,
    isFavorite: Boolean = true,
    onFavoriteClick: () -> Unit,
    onHotelCardClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = modifier
            .width(260.dp)
            .padding(start = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        onClick = onHotelCardClick,

        ) {
        Column {
            // Image section
            Box {
                AsyncImage(
                    model = hotel.imageUrl ?: hotel.imageRes,
                    contentDescription = hotel.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(180.dp)
                        .fillMaxWidth()
                )

                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(28.dp)                    // REAL size
                        .align(Alignment.TopEnd)
                        .background(Color.White, CircleShape)
                        .clickable { onFavoriteClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite",
                        tint = Color.Red,
                        modifier = Modifier.size(14.dp)
                    )
                }

            }

            // Content section
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = hotel.name,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        modifier = Modifier.weight(1f)
                    )

                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFFFFC107),
                        modifier = Modifier.size(16.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = hotel.rating,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = hotel.location,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row {
                    Text(
                        text = hotel.pricePerNight,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = " / night",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}


val filterList = listOf(
    Filters(
        id = 1,
        filterName = "Hotel",
        filterIcon = R.drawable.icon
    ),
    Filters(
        id = 2,
        filterName = "Hotel",
        filterIcon = R.drawable.icon
    ),
    Filters(
        id = 3,
        filterName = "Hotel",
        filterIcon = R.drawable.icon
    ),
    Filters(
        id = 4,
        filterName = "Hotel",
        filterIcon = R.drawable.icon
    ),
    Filters(
        id = 5,
        filterName = "Hotel",
        filterIcon = R.drawable.icon
    ),
    Filters(
        id = 6,
        filterName = "Hotel",
        filterIcon = R.drawable.icon
    ),
    Filters(
        id = 7,
        filterName = "Hotel",
        filterIcon = R.drawable.icon
    )
)



