package com.domocodetech.smarthome.mqtt

import MQTTManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.eclipse.paho.client.mqttv3.IMqttMessageListener

@Composable
fun MQTTListener(mqttManager: MQTTManager) {
    var serverUri by remember { mutableStateOf("") }
    var topic by remember { mutableStateOf("") }
    var receivedMessage by remember { mutableStateOf("") }
    var showErrorDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        TextField(
            value = serverUri,
            onValueChange = { serverUri = it },
            label = { Text("Server URI") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = topic,
            onValueChange = { topic = it },
            label = { Text("Topic") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (serverUri.isNotEmpty() && topic.isNotEmpty()) {
                    mqttManager.subscribe(topic, listener = IMqttMessageListener { _, message ->
                        receivedMessage = String(message.payload)
                    })
                } else {
                    showErrorDialog = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Start Listening")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text("Received message: $receivedMessage")

        if (showErrorDialog) {
            AlertDialog(
                onDismissRequest = { showErrorDialog = false },
                title = { Text("Error") },
                text = { Text("Please provide Server URI and Topic.") },
                confirmButton = {
                    Button(
                        onClick = { showErrorDialog = false }
                    ) {
                        Text("OK")
                    }
                }
            )
        }
    }
}
