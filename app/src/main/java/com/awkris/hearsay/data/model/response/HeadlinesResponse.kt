package com.awkris.hearsay.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class HeadlinesResponse(
    val articles: List<ArticleResponse>,
    val status: String,
    val totalResults: Int
)