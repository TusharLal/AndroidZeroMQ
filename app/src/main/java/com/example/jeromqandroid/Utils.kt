package com.example.jeromqandroid

import android.os.Bundle
import android.os.Handler
import android.os.Message

/**
 *Utility class for storing all the constants
 *
 * @author Tushar Lal
 */
class Utils {
    companion object {
        const val payloadKey: String = "payloadKey"
        const val zmqWait: Int = 0
        const val tcpAddress: String = "tcp://127.0.0.1:5556"

        fun bundledMessage(uiThreadHandler: Handler, msg: String): Message {
            val message: Message = uiThreadHandler.obtainMessage()
            val bundle = Bundle()
            bundle.putString(payloadKey, msg)
            message.data = bundle
            return message
        }
    }
}