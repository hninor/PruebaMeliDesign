package com.hninor.pruebamelidesign.core.designsystem.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hninor.pruebamelidesign.ui.home.formatCurrency

@Composable
fun ProductPriceDisplay(
    price: Double,
    discount: String? = null,
    modifier: Modifier = Modifier,
    priceFontSize: Int = 32,
    discountFontSize: Int = 13,
    showCurrency: Boolean = true
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = formatCurrency(price, if (showCurrency) "$ " else ""),
            fontWeight = FontWeight.SemiBold,
            fontSize = priceFontSize.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        if (discount != null) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = discount,
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = discountFontSize.sp
            )
        }
    }
} 