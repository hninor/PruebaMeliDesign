package com.hninor.pruebamelidesign.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OfficialStoreBadge(
    brand: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Black,
    textColor: Color = Color.White,
    fontSize: Int = 11,
    fontWeight: FontWeight = FontWeight.Bold
) {
    Text(
        text = "${brand.uppercase()} TIENDA OFICIAL",
        color = textColor,
        fontSize = fontSize.sp,
        fontWeight = fontWeight,
        maxLines = 1,
        modifier = modifier
            .background(backgroundColor, RoundedCornerShape(3.dp))
            .padding(horizontal = 6.dp, vertical = 2.dp)
    )
} 