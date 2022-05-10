package com.example.jeromqandroid

import android.os.Bundle
import android.os.Looper
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.jeromqandroid.communication.layer.MessageReceiver
import com.example.jeromqandroid.communication.layer.MessageSender
import com.example.jeromqandroid.handler.MessageListenerHandler
import com.example.jeromqandroid.interfaces.MessageListener


class MainActivity : AppCompatActivity() {

    private var textViewConsole: TextView? = null
    private var editTextMessage: EditText? = null

    private var messageSender: MessageSender? = null
    private var messageReceiver: MessageReceiver?=null

    // Handler for handling messages received from message queue
    private val receiverHandler = MessageListenerHandler(
        Looper.getMainLooper(),
        object : MessageListener {
            override fun onMessageReceived(messageBody: String) {
                textViewConsole?.append(">>> Message received (From ZeroMQ): $messageBody \n")
            }
        }, Utils.payloadKey
    )

    // Handler for handling messages sent to message queue
    private val senderHandler = MessageListenerHandler(
        Looper.getMainLooper(),
        object : MessageListener {
            override fun onMessageReceived(messageBody: String) {
                textViewConsole?.append(">>> Message received (To ZeroMq): $messageBody \n")
            }
        }, Utils.payloadKey
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        messageSender = MessageSender(senderHandler)
        messageReceiver = MessageReceiver(receiverHandler)
        messageReceiver!!.startReceiver()

        initUi()
    }

    /**
     * Method to initialize all UI components
     */
    private fun initUi() {
        textViewConsole = findViewById(R.id.text_view_console)
        editTextMessage = findViewById(R.id.edit_text_message)

        findViewById<View>(R.id.button_send_message).setOnClickListener {
            val message = (editTextMessage as EditText)
            messageSender?.sendMessage(message.text.toString())
            message.text.clear()
        }
    }

    override fun onDestroy() {
        messageReceiver!!.stopReceiver()
        super.onDestroy()
    }
}