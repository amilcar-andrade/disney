package com.example.disney.api

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    @GET("v1/public/comics")
    fun comics(@Query("ts")ts: String,
               @Query("apikey") apiKey: String,
               @Query("hash") hash: String
    ): Single<Response<Envelop>>
}