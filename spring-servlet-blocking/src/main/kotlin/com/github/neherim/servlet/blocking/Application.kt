package com.github.neherim.servlet.blocking

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import java.util.concurrent.Callable
import java.util.concurrent.Executors

/**
 * Tomcat http server example. Thread per connection.
 */
@SpringBootApplication
class Application

data class Tweet(val user: String, val text: List<String>)
data class Reddit(val user: String, val title: String, val text: List<String>)
data class Feed(val posts: List<String>)

@RestController
class FeedController(
        @Value("\${service.twitter.url}")
        private val twitterHost: String,
        @Value("\${service.reddit.url}")
        private val redditHost: String) {

    private val restTemplate = RestTemplate()
    private val threadPool = Executors.newCachedThreadPool()

    // Two parallel requests to external service
    @GetMapping("/feed")
    fun feed(): Feed {
        val twitterResponseFuture = threadPool.submit(Callable {
            restTemplate.getForObject<Tweet>(twitterHost + "/tweet")
        })

        val redditResponseFuture = threadPool.submit(Callable {
            restTemplate.getForObject<Reddit>(redditHost + "/reddit")
        })

        val twitterPosts = twitterResponseFuture.get()!!.text
        val redditPosts = redditResponseFuture.get()!!.text

        return Feed(twitterPosts.plus(redditPosts))
    }

    // One requests to external service
    @GetMapping("/tweet")
    fun tweet(): Feed {
        val tweet = restTemplate.getForObject<Tweet>(twitterHost + "/tweet")!!
        return Feed(tweet.text)
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

