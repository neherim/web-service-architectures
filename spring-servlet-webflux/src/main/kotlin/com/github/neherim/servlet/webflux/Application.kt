package com.github.neherim.servlet.webflux

import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono
import reactor.ipc.netty.http.server.HttpServer


/**
 * Пример сервера Spring WebFlux в функциональном стиле без поднятия контекста, бинов и аннотаций.
 * В качестве web сервера - Netty
 */
class FeedHandler {

    data class Tweet(val user: String, val text: String)
    data class Reddit(val user: String, val title: String, val text: String)
    data class Feed(val posts: List<String>)

    private val webClient: WebClient = WebClient.create("http://localhost:9000")

    fun feed(req: ServerRequest): Mono<ServerResponse> {
        val tweetMono = webClient.get().uri("/tweet?user=Carl")
                .retrieve().bodyToMono<Tweet>()

        val redditMono = webClient.get().uri("/reddit?user=Carl")
                .retrieve().bodyToMono<Reddit>()

        val result: Mono<Feed> = tweetMono.zipWith(redditMono, { r, t ->
            Feed(listOf(t.text, r.text))
        })

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(result, Feed::class.java)
    }
}

fun main(args: Array<String>) {
    val feedHandler = FeedHandler()
    val httpHandler = RouterFunctions.toHttpHandler(apis(feedHandler))
    val adapter = ReactorHttpHandlerAdapter(httpHandler)
    HttpServer.create(8080).newHandler(adapter).block()
    Thread.currentThread().join()
}

fun apis(feedHandler: FeedHandler) = router {
    (accept(APPLICATION_JSON)).nest {
        GET("/feed", feedHandler::feed)
    }
}
