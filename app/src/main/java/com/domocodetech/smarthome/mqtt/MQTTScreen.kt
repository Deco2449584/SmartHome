import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.domocodetech.smarthome.mqtt.MQTTListener
import com.domocodetech.smarthome.mqtt.MQTTSender

@Composable
fun MQTTScreen(mqttManager: MQTTManager) {
    Column {
        MQTTSender(mqttManager = mqttManager)
        MQTTListener(mqttManager = mqttManager)

    }
}
