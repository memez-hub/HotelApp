package com.example.hotelapp.ui.detail

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.hotelapp.R
import com.example.hotelapp.ui.bottomSheets.BookingBottomSheet
import com.example.hotelapp.ui.components.BookingNowButton
import com.example.hotelapp.ui.components.FeatureChip
import com.example.hotelapp.ui.components.PreviewCard
import com.example.hotelapp.ui.components.RatingChip
import com.example.hotelapp.ui.detail.components.DetailScreenTopBar
import com.example.hotelapp.ui.theme.Gray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    onBackButtonClick: () -> Unit,
) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState
        ) {
            BookingBottomSheet(
                onClose = { showSheet = false }
            )
        }
    }

    Scaffold(topBar = {
        DetailScreenTopBar(
            onBackButtonClick = onBackButtonClick,
            scrollBehavior = scrollBehavior,
            onDetailsButtonClick = {}
        )
    }) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.513f)
                        .padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.hotelimage),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }
            item { FeatureSection() }
            item {
                HotelHeadline(
                    hotelName = "Hotel",
                    pricePerNight = "323.2",
                    location = "NYC"
                )
            }
            item {
                HotelDescription(
                    description = "Aston Hotel, Alice Springs NT 0870," +
                            " Australia is a modern hotel," +
                            " elegant 5 star hotel overlooking the sea," +
                            " perfect for a romantic, charming getaway." +
                            "Aston Hotel, Alice Springs NT 0870," +
                            " Australia is a modern hotel," +
                            " elegant 5 star hotel overlooking the sea," +
                            " perfect for a romantic, charming getaway."
                )
            }
            item { PreviewSection(imageList) }
            item {
                BookingNowButton(
                    onClick = { showSheet = true }
                )
            }
        }

    }
}

@Composable
private fun FeatureSection(modifier: Modifier = Modifier) {
    LazyRow(modifier = modifier.padding(12.dp)) {
        item {
            FeatureChip(
                icon = Icons.Default.Notifications,
                text = "chip"
            )
        }
        item {
            FeatureChip(
                icon = Icons.Default.Notifications,
                text = "chip"
            )
        }
        item {
            FeatureChip(
                icon = Icons.Default.Notifications,
                text = "chip"
            )
        }
        item {
            RatingChip(
                rating = "2.6"
            )
        }
    }
}

@Composable
private fun HotelHeadline(
    hotelName: String,
    pricePerNight: String,
    location: String,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = hotelName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = pricePerNight,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = " /night",
                style = MaterialTheme.typography.titleMedium,
                color = Gray
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(16.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = location,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun HotelDescription(
    description: String,
    modifier: Modifier = Modifier,
    minimizedMaxLines: Int = 3,
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(
            text = "Description",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier.animateContentSize(),
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = if (isExpanded) Int.MAX_VALUE else minimizedMaxLines,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = if (isExpanded) "Read Less" else "Read More",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .clickable { isExpanded = !isExpanded }
        )
    }
}

@Composable
fun PreviewSection(
    images: List<Int>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Text(
            text = "Preview",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(images) { image ->
                PreviewCard(image)
            }
        }
    }
}

val imageList = listOf(
    R.drawable.hotelimage,
    R.drawable.hotelimage,
    R.drawable.hotelimage,
    R.drawable.hotelimage,
    R.drawable.hotelimage
)