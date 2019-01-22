package com.sivianes.kotlinkleankode.app.base

import android.app.Application
import com.sivianes.kotlinkleankode.di.interactorModule
import com.sivianes.kotlinkleankode.di.pictureOfTheDayModule
import com.sivianes.kotlinkleankode.di.repositoryModule
import org.koin.android.ext.android.startKoin

class KleanApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(repositoryModule, interactorModule, pictureOfTheDayModule))
    }
}