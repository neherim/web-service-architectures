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

    data class Tweet(val user: String, val text: List<String>)
    data class Reddit(val user: String, val title: String, val text: List<String>)
    data class Feed(val posts: List<String>)

    private val twitterClient: WebClient = WebClient.create("http://localhost:9000")
    private val redditClient: WebClient = WebClient.create("http://localhost:9001")

    // Two parallel requests to external service
    fun feed(req: ServerRequest): Mono<ServerResponse> {
        val tweetMono = twitterClient.get().uri("/tweet?user=John")
                .retrieve().bodyToMono<Tweet>()

        val redditMono = redditClient.get().uri("/reddit?user=John")
                .retrieve().bodyToMono<Reddit>()


        val tweetTextFlux = tweetMono.flatMapIterable { it.text }
        val redditTextFlux = redditMono.flatMapIterable { it.text }


        val feedMono = tweetTextFlux.mergeWith(redditTextFlux)
                .collectList()
                .map { textList -> Feed(textList) }

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(feedMono, Feed::class.java)
    }

    // One requests to external service
    fun tweet(req: ServerRequest): Mono<ServerResponse> {
        val tweetMono = twitterClient.get().uri("/tweet?user=John")
                .retrieve().bodyToMono<Tweet>()

        val feedMono = tweetMono.map { t -> Feed(t.text) }


        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(feedMono, Feed::class.java)
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
    accept(APPLICATION_JSON).nest {
        GET("/feed", feedHandler::feed)
        GET("/tweet", feedHandler::tweet)
    }
}
