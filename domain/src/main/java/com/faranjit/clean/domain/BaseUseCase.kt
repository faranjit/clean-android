package com.faranjit.clean.domain

import com.faranjit.clean.core.exception.UnauthorizedException
import com.faranjit.clean.core.network.BaseResponse
import com.faranjit.clean.core.network.ResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Bulent Turkmen on 11.04.2020.
 */
abstract class BaseUseCase<Response : BaseResponse, in Params> {

    abstract suspend fun buildUseCase(params: Params? = null): ResponseWrapper<Response>

    open suspend fun execute(params: Params? = null): Response {

        return withContext(Dispatchers.IO) {
            when (val wrapped = buildUseCase(params)) {
                is ResponseWrapper.Success -> wrapped.data
                is ResponseWrapper.Unauthorized -> throw UnauthorizedException()
                else -> throw RuntimeException(wrapped.toString())
            }
        }
    }
}