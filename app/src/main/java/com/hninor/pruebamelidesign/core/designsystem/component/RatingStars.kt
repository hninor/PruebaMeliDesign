package com.hninor.pruebamelidesign.core.designsystem.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import com.hninor.pruebamelidesign.R

@Composable
fun RatingStars(
    rating: Double,
    reviews: Int? = null,
    modifier: Modifier = Modifier,
    starColor: Color = MaterialTheme.colorScheme.secondary,
    starSize: Int = 13
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = String.format("%.1f", rating),
            color = MaterialTheme.colorScheme.outline,
            fontSize = 13.sp
        )
        Spacer(modifier = Modifier.width(2.dp))
        val fullStars = rating.toInt()
        val hasHalfStar = (rating - fullStars) >= 0.25 && (rating - fullStars) < 0.75
        val emptyStars = 5 - fullStars - if (hasHalfStar) 1 else 0
        repeat(fullStars) {
            Icon(
                painter = painterResource(id = R.drawable.star_rate),
                contentDescription = null,
                tint = starColor,
                modifier = Modifier.width(starSize.dp)
            )
        }
        if (hasHalfStar) {
            Icon(
                painter = painterResource(id = R.drawable.star_half),
                contentDescription = null,
                tint = starColor,
                modifier = Modifier.width(starSize.dp)
            )
        }
        repeat(emptyStars) {
            Icon(
                painter = painterResource(id = R.drawable.star_outline),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.outline,
                modifier = Modifier.width(starSize.dp)
            )
        }
        if (reviews != null) {
            Spacer(modifier = Modifier.width(4.dp))
            Text("($reviews)", fontSize = 12.sp, color = MaterialTheme.colorScheme.outline)
        }
    }
} 