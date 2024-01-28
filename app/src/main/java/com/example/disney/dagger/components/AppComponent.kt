package com.example.disney.dagger.components

import com.example.disney.dagger.modules.NetworkModule
import com.example.disney.dagger.modules.RxSchedulersModule
import com.example.disney.ui.ComicListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RxSchedulersModule::class])
interface AppComponent {

    fun inject(fragment: ComicListFragment)
}