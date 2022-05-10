package com.example.jeromqandroid.communication.layer.jmq

import android.os.Handler
import com.example.jeromqandroid.Utils
import org.zeromq.SocketType

import org.zeromq.ZMQ

/**
 * Class for receiving message from ZeroMQ
 *
 * @param uiThreadHandler: handler to communicate with UI thread
 *
 * @author Tushar Lal
 */
class ZMQMessageReceiver(private val uiThreadHandler: Handler) : Runnable {

    override fun run() {
        val context = ZMQ.context(1)
        val socket = context.socket(SocketType.REP)
        socket.bind(Utils.tcpAddress)

        while (!Thread.currentThread().isInterrupted) {
            val msg = socket.recv(Utils.zmqWait)
            uiThreadHandler.sendMessage(Utils.bundledMessage(uiThreadHandler, String(msg)))
        }

        socket.close()
        context.term()
    }
}