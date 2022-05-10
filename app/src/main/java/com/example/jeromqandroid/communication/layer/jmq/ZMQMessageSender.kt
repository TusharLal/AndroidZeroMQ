package com.example.jeromqandroid.communication.layer.jmq

import android.os.Handler
import com.example.jeromqandroid.Utils
import org.zeromq.SocketType
import org.zeromq.ZMQ

/**
 * Class for sending message to ZeroMQ
 *
 * @param uiThreadHandler: handler to communicate with UI thread
 * @param message : string message to be sent, to ZeroMQ
 *
 * @author Tushar Lal
 */
class ZMQMessageSender(
    private val uiThreadHandler: Handler,
    private val message: String
) : Runnable {

    override fun run() {
        val context: ZMQ.Context = ZMQ.context(1)
        val socket: ZMQ.Socket = context.socket(SocketType.REQ)
        socket.connect(Utils.tcpAddress)
        socket.send(message.toByteArray(ZMQ.CHARSET), Utils.zmqWait)

        uiThreadHandler.sendMessage(Utils.bundledMessage(uiThreadHandler, message))

        socket.close()
        context.close()
    }

}