// MainActivity.kt
package com.domocodetech.smarthome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.domocodetech.smarthome.composables.LightButton
import com.domocodetech.smarthome.ui.theme.SmartHomeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartHomeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    val mqttManager = MQTTManager()
//                    MQTTScreen(mqttManager = mqttManager)
                    var isLightOn by remember { mutableStateOf(false) }

                    LightButton(
                        onClick = { isLightOn = !isLightOn },
                        isOn = isLightOn,
                        label = "Living Room"
                    )

                }
            }
        }
    }
}

