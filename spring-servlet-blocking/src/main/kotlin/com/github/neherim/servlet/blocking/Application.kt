package com.github.neherim.servlet.blocking

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

/**
 * Пример сервера с архитектурой один поток на одно соединение.
 * В качестве http сервера - Tomcat
 */
@SpringBootApplication
class Application

@RestController
class FeedController {

    data class Tweet(val user: String, val text: String)
    data class Reddit(val user: String, val title: String, val text: String)
    data class Feed(val posts: List<String>)

    private val restTemplate = RestTemplate()
    private val serviceHost = "http://localhost:9000"

    @GetMapping("/feed")
    fun feed(): Feed {
        val tweet = restTemplate.getForObject<Tweet>(serviceHost + "/tweet?user=Carl")!!
        val reddit = restTemplate.getForObject<Reddit>(serviceHost + "/reddit?user=Carl")!!
        return Feed(listOf(tweet.text, reddit.text))
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

