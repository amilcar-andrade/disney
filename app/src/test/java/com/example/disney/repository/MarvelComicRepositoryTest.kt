package com.example.disney.repository

import com.example.disney.api.Data
import com.example.disney.api.Envelop
import com.example.disney.api.MarvelApi
import com.example.disney.api.ResultItem
import com.example.disney.api.Thumbnail
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.TestObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Test
import retrofit2.Response

class MarvelComicRepositoryTest {

    @Test
    fun success() {
        val api: MarvelApi = FakeMarvelApi()
        val toTest = MarvelComicRepository(api, Schedulers.trampoline())
        val observer: TestObserver<List<ResultItem>> = toTest.comics().test()
        observer.assertValue(listOf(ResultItem("a", "b", Thumbnail("c","d"))))
    }

    @Test
    fun errorResponse() {
        val api: MarvelApi = FakeMarvelApi(forceError = true)
        val toTest = MarvelComicRepository(api, Schedulers.trampoline())
        val observer: TestObserver<List<ResultItem>> = toTest.comics().test()
        observer.assertValue(emptyList())
    }

    class FakeMarvelApi(
        private val forceError: Boolean = false
    ) : MarvelApi {
        override fun comics(ts: String, apiKey: String, hash: String): Single<Response<Envelop>> {
            if (forceError) {
                return Single.error(NullPointerException())
            }

            return Single.just(
                Response.success(
                    Envelop("200", Data(
                        listOf(
                            ResultItem("a", "b", Thumbnail("c", "d"))
                        )
                    ))
                )
            )
        }
    }

}