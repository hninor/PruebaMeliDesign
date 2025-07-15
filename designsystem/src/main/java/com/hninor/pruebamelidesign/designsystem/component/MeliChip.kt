package com.hninor.pruebamelidesign.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MeliChip(
    text: String,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    checked: Boolean = false,
    onCheckedChange: (() -> Unit)? = null
) {
    val backgroundColor = if (checked) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.background
    val textColor = if (checked) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurfaceVariant
    val iconTint = textColor
    val borderColor = if (checked) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.outline

    Row(
        modifier = modifier
            .semantics(mergeDescendants = true) {
                contentDescription = text
                stateDescription = if (checked) "Seleccionado" else "No seleccionado"
                role = Role.Checkbox
            }
            .toggleable(
                value = checked,
                enabled = onCheckedChange != null,
                onValueChange = { onCheckedChange?.invoke() }
            )
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(20.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null, // decorativo
                tint = iconTint,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
        }

        Text(
            text = text,
            fontSize = 13.sp,
            color = textColor,
            fontWeight = FontWeight.Normal
        )

        if (checked) {
            Spacer(modifier = Modifier.width(6.dp))
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null, // decorativo, ya se indica el estado arriba
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}