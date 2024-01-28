package com.example.disney.repository

import com.example.disney.api.Envelop
import com.example.disney.api.MarvelApi
import com.example.disney.api.ResultItem
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Named

class MarvelComicRepository @Inject constructor(
    private val api: MarvelApi,
    @Named("io") private val io: Scheduler
    ) {

    fun comics(): Single<List<ResultItem>> {
        return api.comics(TS, PUBLIC_KEY, HASH).map {
            if (it.isSuccessful) {
                val body: Envelop? = it.body()
                if (body == null) {
                    return@map emptyList<ResultItem>()
                }
                return@map body.data?.results ?: emptyList()
            }
            return@map emptyList<ResultItem>()
        }.onErrorReturnItem(emptyList()).subscribeOn(io)
    }

    companion object {
        const val TS: String = "1"
        const val PUBLIC_KEY: String = "6894759015f060b9ba859c4ffbb3405c"
        const val HASH: String = "81873308e9b0a482f3a9715e6e648a54"
    }
}