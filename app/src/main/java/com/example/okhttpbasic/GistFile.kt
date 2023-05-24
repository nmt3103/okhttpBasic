package com.example.okhttpbasic

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GistFile(var content: String?)
