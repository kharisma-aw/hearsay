package com.awkris.hearsay.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ArticleResponse(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String,
    val source: SourceResponse,
    val title: String,
    val url: String,
    val urlToImage: String?
)