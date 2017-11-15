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
    val buffer = ByteBuffer.allocate(256)
    val selector = Selector.open()
    val serverSocket = ServerSocketChannel.open()
    serverSocket.bind(InetSocketAddress(8080))
    serverSocket.configureBlocking(false)
    serverSocket.register(selector, SelectionKey.OP_ACCEPT)

    while (true) {
        selector.select()
        selector.selectedKeys().forEach {
            when {
                it.isAcceptable -> processAcceptEvent(selector, serverSocket)
                it.isReadable -> processReadEvent(buffer, it)
                it.isWritable -> processWriteEvent(buffer, it)
            }
        }
        selector.selectedKeys().clear()
    }
}

fun processWriteEvent(buffer: ByteBuffer, it: SelectionKey) {
    TODO("not implemented")
}

fun processReadEvent(buffer: ByteBuffer, key: SelectionKey) {
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