package com.awkris.hearsay.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class SourceResponse(
    val id: String,
    val name: String
)