package com.example.hotelapp.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FilterBottomSheet(
    onApply: () -> Unit,
    onReset: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Text(
            "Filter Hotel",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(16.dp))

        CountrySection()
        SortSection()
        PriceRangeSection()
        StarRatingSection()
        FacilitiesSection()
        AccommodationSection()

        Spacer(Modifier.height(24.dp))

        BottomActions(
            onReset = onReset,
            onApply = onApply
        )
    }
}

@Composable
private fun CountrySection() {
    var selectedCountry by remember { mutableStateOf<String?>("France") }
    SectionHeader("Country", "See All")

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        listOf("France", "Italia", "Turkey", "Germany").forEach { country ->
            FilterChip(
                selected = country == selectedCountry,
                onClick = {
                    selectedCountry =
                        if (selectedCountry == country) null else country
                },
                label = { Text(country) }
            )
        }
    }

    Spacer(Modifier.height(16.dp))
}

@Composable
private fun SortSection() {
    var selectedFilter by remember { mutableStateOf<String?>("Highest Popularity") }

    SectionHeader("Sort Results")

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        listOf("Highest Popularity", "Highest Price", "Lowest Price").forEach { filter ->
            FilterChip(
                selected = filter == selectedFilter,
                onClick = {
                    selectedFilter =
                        if (selectedFilter == filter) null else filter
                },
                label = { Text(filter) }
            )
        }
    }

    Spacer(Modifier.height(16.dp))
}

@Composable
private fun PriceRangeSection() {
    SectionHeader("Price Range Per Night")

    var range by remember { mutableStateOf(18f..50f) }

    RangeSlider(
        value = range,
        onValueChange = { range = it },
        valueRange = 0f..100f,
        steps = 4
    )

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        PriceChip("$${range.start.toInt()}")
        PriceChip("$${range.endInclusive.toInt()}")
    }

    Spacer(Modifier.height(16.dp))
}

@Composable
private fun StarRatingSection() {
    var selectedRating by remember { mutableStateOf<Int?>(null) }
    SectionHeader("Star Rating")

    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        (5 downTo 1).forEach { star ->
            FilterChip(
                selected = star == selectedRating,
                onClick = {
                    selectedRating =
                        if (selectedRating == star) null else star
                },
                label = {
                    Row {
                        Icon(Icons.Default.Star, null, modifier = Modifier.size(14.dp))
                        Text(" $star")
                    }
                }
            )
        }
    }

    Spacer(Modifier.height(16.dp))
}

@Composable
private fun FacilitiesSection() {

    val facilities = listOf(
        "Wifi",
        "Swimming Pool",
        "Parking",
        "Restaurant"
    )

    var selectedFacilities by remember { mutableStateOf(setOf<String>()) }

    SectionHeader("Facilities", "See All")

    FlowRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        facilities.forEach { facility ->
            FacilityItem(
                label = facility,
                checked = facility in selectedFacilities,
                onCheckChange = { checked ->
                    selectedFacilities =
                        if (checked)
                            selectedFacilities + facility
                        else
                            selectedFacilities - facility
                }

            )
        }
    }

    Spacer(Modifier.height(16.dp))
}

@Composable
fun AccommodationSection() {
    val accommodationTypes = listOf(
        "Hotels",
        "Resorts",
        "Villas",
        "Apartments"
    )

    var selectedAccommodationTypes by remember { mutableStateOf(setOf<String>()) }

    SectionHeader("Accommodation Type", "See All")

    FlowRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        accommodationTypes.forEach { accommodationType ->
            FacilityItem(
                label = accommodationType,
                checked = accommodationType in selectedAccommodationTypes,
                onCheckChange = { checked ->
                    selectedAccommodationTypes =
                        if (checked)
                            selectedAccommodationTypes + accommodationType
                        else
                            selectedAccommodationTypes - accommodationType
                }

            )
        }
    }
}


@Composable
private fun FacilityItem(label: String, checked: Boolean, onCheckChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckChange
        )
        Text(label)
    }
}


@Composable
private fun BottomActions(
    onReset: () -> Unit,
    onApply: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedButton(
            modifier = Modifier.weight(1f),
            onClick = onReset
        ) {
            Text("Reset")
        }

        Button(
            modifier = Modifier.weight(1f),
            onClick = onApply
        ) {
            Text("Apply Filter")
        }
    }
}

@Composable
private fun SectionHeader(title: String, action: String? = null) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title, style = MaterialTheme.typography.titleMedium)
        action?.let {
            Text(
                it,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Composable
private fun PriceChip(text: String) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.primary
    ) {
        Text(
            text,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelSmall
        )
    }
}


