package com.faranjit.clean.core.network

/**
 * Created by Bulent Turkmen on 30.03.2020.
 *
 * This class wraps api response and returns the client after maps the response.
 */
sealed class ResponseWrapper<out T : BaseResponse> {
    /**
     * Success class is using for successful api calls.
     */
    data class Success<out T : BaseResponse>(val data: T) : ResponseWrapper<T>()

    /**
     * When api returns 401, the response should be mapped to this to refresh access tokens.
     */
    data class Unauthorized(val errorMessage: String?) : ResponseWrapper<Nothing>()

    /**
     * Error class which encapsulates general errors
     */
    data class ServiceError(
        val httpCode: Int? = null,
        val errorMessage: String? = null,
        val errorCode: Int? = null
    ) : ResponseWrapper<Nothing>()

    /**
     * Throw this when device has no connection.
     */
    object NetworkError : ResponseWrapper<Nothing>()
}