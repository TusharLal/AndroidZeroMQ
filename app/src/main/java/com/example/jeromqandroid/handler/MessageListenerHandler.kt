package com.example.jeromqandroid.handler

import android.os.Handler
import android.os.Looper
import android.os.Message
import com.example.jeromqandroid.interfaces.MessageListener

/**
 *
 * @param looper: thread for handling UI events
 * @param messageListener: interface for passing message
 * @param message: string message to be passed
 *
 * @author Tushar Lal
 */
class MessageListenerHandler(
    looper: Looper,
    private val messageListener: MessageListener,
    private val message: String
) : Handler(looper) {

    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        msg.data.getString(message)?.let { messageListener.onMessageReceived(it) }
    }
}