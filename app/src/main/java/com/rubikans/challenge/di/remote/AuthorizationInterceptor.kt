package com.rubikans.challenge.di.remote

import android.content.Context
import com.rubikans.challenge.di.annotations.qualifiers.AppContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
    @AppContext private val context: Context,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder()


//        if (!token.isNullOrEmpty()) {
//            request.header("Authorization", "Bearer $token")
//        }

//        request.header("lang", lang)

        request.header("Content-Type", "application/json")
            .method(original.method, original.body)

        return chain.proceed(request.build())

    }
}