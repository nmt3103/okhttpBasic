package com.example.okhttpbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.os.IResultReceiver.Default
import android.util.Log
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope

import com.example.okhttpbasic.databinding.ActivityMainBinding
import com.squareup.moshi.JsonAdapter

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request


import okio.IOException


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val client = OkHttpClient()
    private val moshi = Moshi.Builder().build()
    private val type = Types.newParameterizedType(Gist::class.java)
    private val jsonAdapter: JsonAdapter<Gist> = moshi.adapter(type)





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch(Dispatchers.IO) {

            val request = Request.Builder()
                .url("https://api.github.com/gists/c2a7c39532239ff261be")
                .build()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                val gist = jsonAdapter.fromJson(response.body!!.source())

                for ((key, value) in gist!!.files!!) {
                    Log.d("MainActivity",key)
                    Log.d("MainActivity",value.content.toString())
                }
            }

        }

        println("master")











    }
    companion object {
        val MEDIA_TYPE_MARKDOWN = "text/x-markdown; charset=utf-8".toMediaType()
    }






}

