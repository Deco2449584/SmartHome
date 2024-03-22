package com.domocodetech.smarthome.mqtt

import MQTTManager
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MQTTSender(mqttManager: MQTTManager) {
    var serverUri by remember { mutableStateOf("") }
    var clientId by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var topic by remember { mutableStateOf("") }
    var messageToSend by remember { mutableStateOf("") }
    var showErrorDialog by remember { mutableStateOf(false) }
    var showUserAndPassFields by remember { mutableStateOf(true) }

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
            value = clientId,
            onValueChange = { clientId = it },
            label = { Text("Client ID") },
            modifier = Modifier.fillMaxWidth()
        )

        if (showUserAndPassFields) {
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        TextField(
            value = topic,
            onValueChange = { topic = it },
            label = { Text("Topic") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = messageToSend,
            onValueChange = { messageToSend = it },
            label = { Text("Message to send") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = SpaceBetween
        ) {
            Checkbox(
                checked = showUserAndPassFields,
                onCheckedChange = { showUserAndPassFields = it },
                modifier = Modifier.padding(end = 8.dp)
            )
            Text("Show Username and Password")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                mqttManager.connect(serverUri, clientId, username, password,
                    onSuccess = {
                        mqttManager.publish(topic, messageToSend)
                        messageToSend = ""
                    },
                    onError = { showErrorDialog = true }
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Send Message")
        }

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
