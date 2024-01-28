package com.example.disney.api

data class Envelop(
    val status: String?,
    val data: Data?
)

data class Data(
    val results: List<ResultItem>?
)

data class ResultItem(
    val title: String? = "",
    val description: String? = "",
    val thumbnail: Thumbnail?
)

data class Thumbnail(
    val path: String? = "",
    val extension: String? = ""
)