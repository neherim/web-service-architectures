package com.github.neherim.nonblocking

import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.SelectionKey
import java.nio.channels.Selector
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel

/**
 * Simple and incorrect nio server implementation
 */
fun main(args: Array<String>) {
    val selector = Selector.open()
    val serverSocket = ServerSocketChannel.open()
    serverSocket.bind(InetSocketAddress(8080))
    serverSocket.configureBlocking(false)
    serverSocket.register(selector, SelectionKey.OP_ACCEPT)
    val buffer = ByteBuffer.allocate(256)

    while (true) {
        selector.select()
        val keys = selector.selectedKeys().iterator()
        while (keys.hasNext()) {
            val key = keys.next()
            keys.remove()
            if (key.isValid) {
                if (key.isAcceptable) {
                    processAcceptEvent(selector, serverSocket)
                }
                if (key.isReadable) {
                    processReadEvent(buffer, key)
                }
            }
        }
    }
}

private fun processReadEvent(buffer: ByteBuffer, key: SelectionKey) {
    buffer.clear()
    val socketChannel = key.channel() as SocketChannel
    val bytesRead = socketChannel.read(buffer)
    val request = String(buffer.array().sliceArray(0 until bytesRead))
    val response = request.toUpperCase()
    socketChannel.write(ByteBuffer.wrap(response.toByteArray()))
    socketChannel.close()
}

fun processAcceptEvent(selector: Selector, serverSocket: ServerSocketChannel) {
    val client = serverSocket.accept()
    if (client != null) {
        client.configureBlocking(false)
        client.register(selector, SelectionKey.OP_READ)
    }
}