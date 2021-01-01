package com.faranjit.clean.domain

import com.faranjit.clean.core.network.BaseResponse
import com.faranjit.clean.core.network.ResponseWrapper
import com.faranjit.clean.domain.base.BaseUnitTest
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.anything
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * Created by Bulent Turkmen on 18.04.2020.
 */
class BaseUseCaseTest : BaseUnitTest() {

    @Test
    fun shouldReturnSuccessResponse() = runBlocking {
        val useCase = FakeUseCase()
        val response = useCase.execute(FakeUseCase.Params(success = true, unauthorized = false))

        assertThat(response, anything())
    }

    @Test
    fun shouldThrowUnauthorizedExceptionWhenUnauthorized() {

    }
}

class FakeUseCase : BaseUseCase<BaseResponse, FakeUseCase.Params>() {

    override suspend fun buildUseCase(params: Params?): ResponseWrapper<BaseResponse> {
        return when {
            params!!.success -> {
                ResponseWrapper.Success(BaseResponse())
            }
            params.unauthorized -> {
                ResponseWrapper.Unauthorized("401")
            }
            else -> {
                ResponseWrapper.ServiceError(500, "general error")
            }
        }
    }

    data class Params(val success: Boolean, val unauthorized: Boolean)

}