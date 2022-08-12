package com.hua.github_app.http

import com.hua.github_app.login.LoginManager
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val token = LoginManager.accessToken
        if (token != null) {
            request = request.newBuilder()
                .addHeader("Authorization", "token $token")
                .build()
        }
        return chain.proceed(request)
    }

}