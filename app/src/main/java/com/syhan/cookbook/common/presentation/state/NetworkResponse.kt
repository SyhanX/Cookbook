package com.syhan.cookbook.common.presentation.state

sealed interface NetworkResponse {
    data object Success : NetworkResponse
    data object InternetConnectionError : NetworkResponse
    data object UnexpectedError : NetworkResponse
    data object Loading : NetworkResponse
}