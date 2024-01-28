package com.example.disney.dagger.modules

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Named

@Module
abstract class RxSchedulersModule {

    companion object {

        @Provides
        @Named("io")
        fun provideIo(): Scheduler = Schedulers.io()
    }
}