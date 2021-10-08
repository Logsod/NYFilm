package com.losman.nyfilm.retrofit

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NYService {
    @GET("all.json")
    fun getFilmList(@Query("offset") offset : Int) : Single<NYListResponse>
}