package com.example.jeromqandroid.interfaces

/**
 * Interface for passing message
 *
 * @author Tushar Lal
 */
interface MessageListener {
    fun onMessageReceived(messageBody: String)
}