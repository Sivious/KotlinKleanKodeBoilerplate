package com.sivianes.kotlinkleankode.app.base

import android.app.Application
import com.sivianes.kotlinkleankode.di.interactorModule
import com.sivianes.kotlinkleankode.di.repositoryModule
import com.sivianes.kotlinkleankode.domain.api.Api
import io.reactivex.disposables.Disposable
import org.koin.android.ext.android.startKoin

class KleanApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(repositoryModule, interactorModule))
    }


}