package com.hua.github_app.http

import androidx.annotation.NonNull
import com.hua.github_app.AppConfig
import com.hua.github_app.http.api.LoginService
import com.hua.github_app.http.api.RepoService
import com.hua.github_app.http.api.SearchService
import com.hua.github_app.http.api.UserService
import com.hua.github_app.utils.LogUtil
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created on 2022/8/9.
 *
 * @author hua
 */
object AppRetrofit {
    private const val TAG = "AppRetrofit"

    private const val GITHUB_BASE_URL = AppConfig.GITHUB_BASE_URL
    private const val GITHUB_API_BASE_URL = AppConfig.GITHUB_API_BASE_URL

    private val retrofitMap by lazy {
        mutableMapOf<String, Retrofit>()
    }

    fun getLoginService(): LoginService {
        return getRetrofit(GITHUB_BASE_URL).create(LoginService::class.java)
    }

    fun getUserService(): UserService {
        return getRetrofit(GITHUB_API_BASE_URL).create(UserService::class.java)
    }

    fun getRepoService(): RepoService {
        return getRetrofit(GITHUB_API_BASE_URL).create(RepoService::class.java)
    }

    fun getSearchService(): SearchService {
        return getRetrofit(GITHUB_API_BASE_URL).create(SearchService::class.java)
    }

    private fun getRetrofit(baseUrl: String): Retrofit {
        var retrofit = retrofitMap[baseUrl]
        if (retrofit != null) {
            return retrofit
        }
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            LogUtil.i(TAG, "http response: $message")
        }.apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.MILLISECONDS)
            .addInterceptor(TokenInterceptor())
            .addInterceptor(loggingInterceptor)
            .build()
        val builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
        retrofit = builder.build()
        retrofitMap[baseUrl] = retrofit
        return retrofit
    }
}