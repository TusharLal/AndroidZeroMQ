package com.example.jeromqandroid.communication.layer

import android.os.Handler
import com.example.jeromqandroid.communication.layer.jmq.ZMQMessageSender

/**
 * Class to send message to message queue on worker thread
 *
 * @param uiThreadHandler: handler to communicate with UI thread
 *
 * @author Tushar Lal
 */
class MessageSender(private val uiThreadHandler: Handler) {

    /**
     * Function to send message to zero message queue
     *
     * @param message: string message
     *
     * @author Tushar Lal
     */
    fun sendMessage(message: String) {
        Thread(
            ZMQMessageSender(
                uiThreadHandler,
                message
            )
        ).start()
    }
}