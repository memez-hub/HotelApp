package com.example.hotelapp.ui.components

import android.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotelapp.ui.theme.ActiveBlue
import com.example.hotelapp.ui.theme.WhiteBlue

@Composable
fun FilterCard(
    filterName: String,
    filterIcon : Int,
    onClick: () -> Unit,
    isActive : Boolean = false


){
    OutlinedButton(
        onClick = { onClick },
        border = BorderStroke(0.dp, Color.Transparent),
        colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues(),
        modifier = Modifier
            .padding(end = 8.dp,)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(
                color = if(isActive) ActiveBlue else WhiteBlue,
                shape = RoundedCornerShape(8.dp)
            )
    ){
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 18.dp,),
            horizontalArrangement = Arrangement.Start

        ){
            Icon(
                imageVector = ImageVector.vectorResource(filterIcon),
                contentDescription = "",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = filterName,
                color = Color(0xFFFFFFFF),
                fontSize = 14.sp,
            )
        }
    }

}