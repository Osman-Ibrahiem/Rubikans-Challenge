package com.rubikans.challenge.di.remote

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.inject.Inject

class ResponseInterceptor @Inject constructor(
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response: Response?

        response = chain.proceed(request)
        val responseString = String(response.body.bytes())

        val body: ResponseBody = responseString.toResponseBody(response.body.contentType())
        return response.newBuilder()
            .body(body = body)
            .build()

    }
}