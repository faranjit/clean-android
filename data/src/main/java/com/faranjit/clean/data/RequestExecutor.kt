package com.faranjit.clean.data

import com.faranjit.clean.core.network.BaseResponse
import com.faranjit.clean.core.network.ResponseWrapper
import com.faranjit.clean.domain.Executor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by Bulent Turkmen on 31.03.2020.
 */
class RequestExecutor : Executor {

    override suspend fun <Response : BaseResponse> call(block: suspend () -> Response): ResponseWrapper<Response> =
        withContext(
            Dispatchers.IO
        ) {
            try {
                ResponseWrapper.Success(block())
            } catch (t: Throwable) {
                when (t) {
                    is IOException -> ResponseWrapper.NetworkError
                    is HttpException -> {
                        val statusCode = t.code()
                        if (statusCode == 401) { // unauthorized
                            ResponseWrapper.Unauthorized(t.message())
                        } else {
                            ResponseWrapper.ServiceError(
                                httpCode = t.code(),
                                errorMessage = t.message()
                            )
                        }
                    }
                    else -> ResponseWrapper.ServiceError(errorMessage = t.message)
                }
            }
        }
}