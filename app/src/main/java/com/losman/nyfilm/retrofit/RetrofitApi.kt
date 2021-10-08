package com.losman.nyfilm.retrofit

import com.losman.nyfilm.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi() {
    private fun getRetrofit(): Retrofit {

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val url = original.url().newBuilder().addQueryParameter("api-key", BuildConfig.API_KEY)
                .build()
            val request = original.newBuilder().url(url).build()
            chain.proceed(request)
        }

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun getServiceApi(): NYService {
        return getRetrofit().create(NYService::class.java)
    }
}