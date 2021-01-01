package com.faranjit.clean.domain

import com.faranjit.clean.core.network.BaseResponse
import com.faranjit.clean.core.network.ResponseWrapper

/**
 * Created by Bulent Turkmen on 31.03.2020.
 */
interface Executor {

    suspend fun <Response : BaseResponse> call(block: suspend () -> Response): ResponseWrapper<Response>
}