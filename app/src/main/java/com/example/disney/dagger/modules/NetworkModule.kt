package com.example.disney.dagger.modules

import com.example.disney.api.MarvelApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
abstract class NetworkModule {

    companion object {

        @Provides
        fun provideService(): MarvelApi {
            // NOTE: All API fields are option, we need to add this adapter to handle that
            val moshiBuilder = Moshi.Builder().add(KotlinJsonAdapterFactory())
            val moshiConverter = MoshiConverterFactory.create(moshiBuilder.build())
            return Retrofit.Builder()
                .baseUrl("https://gateway.marvel.com/")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(moshiConverter)
                .build()
                .create(MarvelApi::class.java)
        }
    }
}