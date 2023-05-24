package com.example.okhttpbasic

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Gist(var files: Map<String, GistFile>?)
