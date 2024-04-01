package com.syhan.cookbook.common.presentation.state

sealed class NetworkResponse(
    val message: String? = null
) {
    data object Success : NetworkResponse()
    class Error(message: String?) : NetworkResponse(message)
    data object Loading : NetworkResponse()
}