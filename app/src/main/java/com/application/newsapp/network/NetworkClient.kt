package com.application.newsapp.network

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkClient {

    private var retrofit: Retrofit? = null
    private const val REQUEST_TIMEOUT = 3
    private var okHttpClient: OkHttpClient? = null

    fun getClient(url: String): Retrofit {

        if (okHttpClient == null)
            initOkHttp()

        if (retrofit == null || retrofit?.baseUrl().toString() != url) {
            retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient!!)
                .build()
        }
        return retrofit!!
    }

    private fun initOkHttp() {
        val httpClient = OkHttpClient().newBuilder()
            .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.MINUTES)
            .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.MINUTES)
            .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.MINUTES)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")

            val request = requestBuilder.build()
            chain.proceed(request)
        }

        httpClient.addInterceptor { chain ->
            val request = chain.request()
            // try the request
            var response = chain.proceed(request)
            var tryCount = 0
            while (!response.isSuccessful && tryCount < 3) {
                Log.d("intercept", "Request is not successful - $tryCount")
                tryCount++
                // retry the request
                response = chain.proceed(request)
            }
            // otherwise just pass the original response on
            response
        }

        okHttpClient = httpClient.build()
    }
}