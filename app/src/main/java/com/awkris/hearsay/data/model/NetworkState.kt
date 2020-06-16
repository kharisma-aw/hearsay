package com.awkris.hearsay.data.model

sealed class NetworkState {
    object Loading : NetworkState()
    object Success : NetworkState()
    class Error(val message: String?) : NetworkState()
}