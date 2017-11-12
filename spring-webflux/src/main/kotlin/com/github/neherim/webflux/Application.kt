package com.github.neherim.webflux

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono


/**
 * Spring WebFlux http server example.
 */
@RestController
class FeedHandler(@Value("\${service.twitter.url}")
                  private val twitterUrl: String,
                  @Value("\${service.reddit.url}")
                  private val redditUrl: String) {

    data class Tweet(val user: String, val text: List<String>)
    data class Reddit(val user: String, val title: String, val text: List<String>)
    data class Feed(val posts: List<String>)

    private val twitterClient: WebClient = WebClient.create(twitterUrl)
    private val redditClient: WebClient = WebClient.create(redditUrl)

    fun getTweet() = twitterClient.get().uri("/tweet").retrieve().bodyToMono<Tweet>()
    fun getReddit() = redditClient.get().uri("/reddit").retrieve().bodyToMono<Reddit>()

    // Two parallel requests to external service
    @GetMapping(value = "/feed", produces = arrayOf(APPLICATION_JSON_VALUE))
    fun feed(): Mono<Feed> {
        val tweetTextFlux = getTweet().flatMapIterable { it.text }
        val redditTextFlux = getReddit().flatMapIterable { it.text }

        return tweetTextFlux.mergeWith(redditTextFlux)
                .collectList()
                .map { Feed(it) }
    }

    // One requests to external service
    @GetMapping(value = "/tweet", produces = arrayOf(APPLICATION_JSON_VALUE))
    fun tweet(): Mono<Feed> {
        return getTweet().map { Feed(it.text) }
    }
}

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
