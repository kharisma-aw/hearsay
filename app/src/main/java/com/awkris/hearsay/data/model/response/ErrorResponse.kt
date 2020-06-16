package com.awkris.hearsay.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val status: String,
    val code: String,
    val message: String
)