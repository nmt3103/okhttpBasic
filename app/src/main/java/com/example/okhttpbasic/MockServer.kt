package com.example.okhttpbasic

import junit.framework.Assert.assertEquals
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer


class MockServer {
    @Throws(Exception::class)
    fun test() {
        // Create a MockWebServer. These are lean enough that you can create a new
        // instance for every unit test.
        val server = MockWebServer()

        // Schedule some responses.
        server.enqueue(MockResponse().setBody("hello, world!"))
        server.enqueue(MockResponse().setBody("sup, bra?"))
        server.enqueue(MockResponse().setBody("yo dog"))

        // Start the server.
        server.start()

        // Ask the server for its URL. You'll need this to make HTTP requests.
        val baseUrl: HttpUrl = server.url("/v1/chat/")


        server.shutdown()
    }
}