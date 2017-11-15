package com.github.neherim.servlet.nonblocking

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.AsyncRestTemplate
import org.springframework.web.context.request.async.DeferredResult
import java.util.*


/**
 * Tomcat http server example.  Non-blocking, thread per request.
 */
@SpringBootApplication
class Application

data class Tweet(val user: String, val text: List<String>)
data class Feed(val posts: List<String>)

@RestController
class FeedController(
        @Value("\${service.twitter.url}")
        private val twitterUrl: String) {

    val restTemplate = AsyncRestTemplate()

    // One requests to external service
    @GetMapping("/tweet")
    fun tweet(): DeferredResult<Feed> {
        val dr = DeferredResult<Feed>()
        val future = restTemplate.exchange(twitterUrl + "/tweet", HttpMethod.GET, entity(), Tweet::class.java)
        future.addCallback(
                { success ->
                    val feed = Feed((success.body.text))
                    dr.setResult(feed)
                },
                { fail -> println(fail) })
        return dr
    }

    fun entity(): HttpEntity<Any> {
        val requestHeaders = HttpHeaders()
        requestHeaders.accept = Arrays.asList(MediaType.APPLICATION_JSON)
        return HttpEntity("parameters", requestHeaders)
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

