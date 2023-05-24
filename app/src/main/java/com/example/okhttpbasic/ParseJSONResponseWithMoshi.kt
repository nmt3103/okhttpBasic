package com.example.okhttpbasic

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class ParseJSONResponseWithMoshi {
    private val client = OkHttpClient()
    private val moshi = Moshi.Builder().build()
    private val gistJsonAdapter = moshi.adapter(Gist::class.java)

    fun run() {
        val request = Request.Builder()
            .url("https://api.github.com/gists/c2a7c39532239ff261be")
            .build()
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            val gist = gistJsonAdapter.fromJson(response.body!!.source())

            for ((key, value) in gist!!.files!!) {
                println(key)
                println(value.content)
            }
        }
    }

    @JsonClass(generateAdapter = true)
    data class Gist(var files: Map<String, GistFile>?)

    @JsonClass(generateAdapter = true)
    data class GistFile(var content: String?)
}