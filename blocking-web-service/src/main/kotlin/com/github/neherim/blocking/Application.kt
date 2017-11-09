package com.github.neherim.blocking

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.Executors

/**
 * Пример сервера с архитектурой "один поток на соединение"
 * Количестов потоков ограничено, потоки распределяются из пула
 */
fun main(args: Array<String>) {
    val threadPool = Executors.newFixedThreadPool(10)
    val server = ServerSocket(8080)
    while (true) {
        val socket = server.accept()
        threadPool.submit(Worker(socket))
    }
}

class Worker(private val socket: Socket) : Runnable {

    override fun run() {
        val requestStream = BufferedReader(InputStreamReader(socket.getInputStream()))
        val request = requestStream.readLine()
        val response = request.toUpperCase()
        val responseStream = PrintWriter(socket.getOutputStream())
        responseStream.print(response)
        socket.close()
    }
}
