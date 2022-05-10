package com.example.jeromqandroid.communication.layer

import android.os.Handler
import com.example.jeromqandroid.communication.layer.jmq.ZMQMessageReceiver

/**
 * Class to start/stop message queue receiver thread
 *
 * @param uiThreadHandler: handler to communicate with UI thread
 *
 * @author Tushar Lal
 */
class MessageReceiver(uiThreadHandler: Handler) {
    private var thread: Thread = Thread(ZMQMessageReceiver(uiThreadHandler))

    /**
     * Function to start message receiver
     *
     * @author Tushar Lal
     */
    fun startReceiver() {
        thread.start()
    }

    /**
     * Function to stop message receiver
     *
     * @author Tushar Lal
     */
    fun stopReceiver() {
        thread.interrupt()
    }
}