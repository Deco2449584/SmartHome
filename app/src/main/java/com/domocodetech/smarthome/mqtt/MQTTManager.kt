// MQTTManager.kt
import org.eclipse.paho.client.mqttv3.IMqttMessageListener
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence

class MQTTManager {

    private var mqttClient: MqttClient? = null
    private var isConnected = false

    fun connect(
        serverUri: String,
        clientId: String,
        username: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        if (isConnected) {
            onSuccess()
            return
        }
        try {
            val options = MqttConnectOptions()
            options.isCleanSession = true
            options.userName = username
            options.password = password.toCharArray()
            mqttClient = MqttClient(serverUri, clientId, MemoryPersistence())
            mqttClient?.connect(options)
            isConnected = true
            onSuccess()
        } catch (ex: MqttException) {
            onError("Error connecting to MQTT server: ${ex.message}")
        }
    }

    fun disconnect() {
        mqttClient?.disconnect()
        isConnected = false
    }

    fun subscribe(topic: String, qos: Int = 1, listener: IMqttMessageListener) {
        mqttClient?.subscribe(topic, qos, listener)
    }

    fun publish(topic: String, payload: String, qos: Int = 1) {
        val message = MqttMessage(payload.toByteArray())
        message.qos = qos
        mqttClient?.publish(topic, message)
    }
}
