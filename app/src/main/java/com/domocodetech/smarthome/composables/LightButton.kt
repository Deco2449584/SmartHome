package com.domocodetech.smarthome.composables
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LightButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isOn: Boolean,
    label: String,
) {
    // Animaciones para el cambio de tamaño y color
    val scale by animateFloatAsState(if (isOn) 1.1f else 1f)
    val backgroundColor by animateColorAsState(if (isOn) Color(0xFF72D8FF) else Color(0xFFEEEEEE))
    val textColor by animateColorAsState(if (isOn) Color.White else Color.Gray)

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .padding(8.dp)
            .fillMaxWidth()
            .border(2.dp, Color.Black)
            .background(color = backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.AddCircle, // Usamos el ícono de bombillo de HomeKit
                contentDescription = "Lightbulb Icon",
                tint = textColor, // Usamos el color animado para el ícono
                modifier = Modifier.scale(scale) // Aplicamos la animación de escala al ícono
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (isOn) "On" else "Off",
                style = MaterialTheme.typography.titleSmall.copy(
                    color = textColor // Usamos el color animado para el texto
                ),
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.titleSmall.copy(
                    color = textColor // Usamos el color animado para el texto
                ),
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}

@Composable
@Preview
fun LightButtonPreview() {
    LightButton(
        onClick = {},
        isOn = true,
        label = "Living Room"
    )
}
