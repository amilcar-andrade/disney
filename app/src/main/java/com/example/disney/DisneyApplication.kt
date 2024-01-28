package com.example.disney

import android.app.Application
import com.example.disney.dagger.components.AppComponent
import com.example.disney.dagger.components.DaggerAppComponent

class DisneyApplication: Application() {

    val component: AppComponent = DaggerAppComponent.create()
}