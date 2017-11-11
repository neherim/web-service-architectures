package com.github.neherim.servlet.blocking

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import java.util.concurrent.Callable
import java.util.concurrent.Executors

/**
 * Пример сервера с архитектурой один поток на одно соединение.
 * В качестве http сервера - Tomcat
 */
@SpringBootApplication
class Application

@RestController
class FeedController {

    data class Tweet(val user: String, val text: List<String>)
    data class Reddit(val user: String, val title: String, val text: List<String>)
    data class Feed(val posts: List<String>)

    private val restTemplate = RestTemplate()
    private val twitterHost = "http://localhost:9000"
    private val redditHost = "http://localhost:9001"
    private val theradPool = Executors.newCachedThreadPool()

    @GetMapping("/feed")
    fun feed(): Feed {
        val twitterResponseFuture = theradPool.submit(Callable {
            restTemplate.getForObject<Tweet>(twitterHost + "/tweet?user=Carl")
        })

        val redditResponseFuture = theradPool.submit(Callable {
            restTemplate.getForObject<Reddit>(redditHost + "/reddit?user=Carl")
        })

        val twitterPosts = twitterResponseFuture.get()!!.text
        val redditPosts = redditResponseFuture.get()!!.text

        return Feed(twitterPosts.plus(redditPosts))
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

