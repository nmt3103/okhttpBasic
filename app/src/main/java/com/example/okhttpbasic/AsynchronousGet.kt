package com.example.okhttpbasic

import android.util.Log
import okhttp3.*
import okio.IOException

class AsynchronousGet{
    private val client = OkHttpClient()

    fun run() {
        val request = Request.Builder()
            .url("https://reqres.in/api/users/2")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("Response", "Failure and ${e.message.toString()}")
//                binding.tvMain.text = "Failure"
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    for ((name, value) in response.headers) {
                        Log.d("Response", "$name : $value")

                    }
                    Log.d("Response", response.body!!.string())


                }
//                runOnUiThread {
//                    binding.tvMain.text = "Success"
//                }

            }
        })

    }
}