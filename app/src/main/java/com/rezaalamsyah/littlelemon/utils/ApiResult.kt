package com.rezaalamsyah.littlelemon.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>
    data class Error(val exception: Throwable) : ApiResult<Nothing>
    object Loading : ApiResult<Nothing>
}

fun <T> Flow<T>.asResult(): Flow<ApiResult<T>> {
    return this
        .map<T, ApiResult<T>> { ApiResult.Success(it) }
        .onStart { emit(ApiResult.Loading) }
        .catch { emit(ApiResult.Error(it)) }
}