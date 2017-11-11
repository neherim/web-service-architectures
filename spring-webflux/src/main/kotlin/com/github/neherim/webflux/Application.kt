package com.github.neherim.webflux

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
 * Spring WebFlux http server in example. In functional style, without spring context and annotations.
 */
class FeedHandler(twitterUrl: String, redditUrl: String) {

    data class Tweet(val user: String, val text: List<String>)
    data class Reddit(val user: String, val title: String, val text: List<String>)
    data class Feed(val posts: List<String>)

    private val twitterClient: WebClient = WebClient.create(twitterUrl)
    private val redditClient: WebClient = WebClient.create(redditUrl)

    fun getTweet() = twitterClient.get().uri("/tweet").retrieve().bodyToMono<Tweet>()
    fun getReddit() = redditClient.get().uri("/reddit").retrieve().bodyToMono<Reddit>()

    // Two parallel requests to external service
    fun feed(req: ServerRequest): Mono<ServerResponse> {
        val tweetTextFlux = getTweet().flatMapIterable { it.text }
        val redditTextFlux = getReddit().flatMapIterable { it.text }

        val feedMono = tweetTextFlux.mergeWith(redditTextFlux)
                .collectList()
                .map { Feed(it) }

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(feedMono, Feed::class.java)
    }

    // One requests to external service
    fun tweet(req: ServerRequest): Mono<ServerResponse> {
        val feedMono = getTweet().map { Feed(it.text) }

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(feedMono, Feed::class.java)
    }
}

fun main(args: Array<String>) {
    println("Server started")
    val twitterUrl = System.getProperty("service.twitter.url")
    val redditUrl = System.getProperty("service.reddit.url")

    val feedHandler = FeedHandler(twitterUrl, redditUrl)
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
