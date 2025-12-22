package com.example.hotelapp.ui.schedule

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotelapp.R
import com.example.hotelapp.model.Schedule
import com.example.hotelapp.ui.components.scheduleList
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import java.time.LocalDate
import java.time.YearMonth


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen(paddingValues: PaddingValues) {

    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    val calendarState = rememberSheetState()

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true
        ),
        selection = CalendarSelection.Period(
            onSelectRange = { startDate, endDate -> }
        )
    )


    LazyColumn(modifier = Modifier.padding(paddingValues)) {

        item { DatePickerRow(onDateButtonClick = { calendarState.show() }) }

        item { MyScheduleRow() }

        scheduleList(
            scheduleList = scheduleList, onScheduleCardClick = {})
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DatePickerRow(
    modifier: Modifier = Modifier,
    onDateButtonClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(bottom = 23.dp, start = 24.dp, end = 24.dp)
            .fillMaxWidth()
    ) {
        Column(
        ) {
            Text(
                "Start date",
                color = Color(0xFF1E1E1E),
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp, end = 46.dp)
            )
            OutlinedButton(
                onClick = { onDateButtonClick() },
                border = BorderStroke(0.dp, Color.Transparent),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(),
                modifier = Modifier
                    .border(
                        width = 1.dp, color = Color(0xFFD9D9D9), shape = RoundedCornerShape(8.dp)
                    )
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(
                        color = Color(0xFFFFFFFF), shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)
                ) {
                    Text(
                        "19.11.25",
                        color = Color(0xFF1E1E1E),
                        fontSize = 16.sp,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.DateRange, contentDescription = ""
                    )
                }
            }
        }
        Column(
        ) {
            Text(
                "End date",
                color = Color(0xFF1E1E1E),
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp, end = 55.dp)
            )
            OutlinedButton(
                onClick = { println("Pressed!") },
                border = BorderStroke(0.dp, Color.Transparent),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(),
                modifier = Modifier
                    .border(
                        width = 1.dp, color = Color(0xFFD9D9D9), shape = RoundedCornerShape(8.dp)
                    )
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(
                        color = Color(0xFFFFFFFF), shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)
                ) {
                    Text(
                        "25.11.25",
                        color = Color(0xFF1E1E1E),
                        fontSize = 16.sp,
                        modifier = Modifier.padding(end = 15.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.DateRange, contentDescription = ""
                    )
                }
            }
        }
    }
}

@Composable
private fun MyScheduleRow(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(bottom = 16.dp, start = 21.dp, end = 21.dp)
            .fillMaxWidth()
    ) {
        Text(
            "My Schedule",
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

val scheduleList = listOf(
    Schedule(
        hotelName = "Hotel",
        pricePerNight = "34.33",
        checkInDate = "12.12.12",
        imageRes = R.drawable.hotelimage
    ), Schedule(
        hotelName = "Hotel",
        pricePerNight = "34.33",
        checkInDate = "12.12.12",
        imageRes = R.drawable.hotelimage
    ), Schedule(
        hotelName = "Hotel",
        pricePerNight = "34.33",
        checkInDate = "12.12.12",
        imageRes = R.drawable.hotelimage
    ), Schedule(
        hotelName = "Hotel",
        pricePerNight = "34.33",
        checkInDate = "12.12.12",
        imageRes = R.drawable.hotelimage
    ), Schedule(
        hotelName = "Hotel",
        pricePerNight = "34.33",
        checkInDate = "12.12.12",
        imageRes = R.drawable.hotelimage
    ), Schedule(
        hotelName = "Hotel",
        pricePerNight = "34.33",
        checkInDate = "12.12.12",
        imageRes = R.drawable.hotelimage
    ), Schedule(
        hotelName = "Hotel",
        pricePerNight = "34.33",
        checkInDate = "12.12.12",
        imageRes = R.drawable.hotelimage
    )
)