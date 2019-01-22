package com.sivianes.kotlinkleankode.di

import com.sivianes.kotlinkleankode.data.UserRepository
import com.sivianes.kotlinkleankode.data.UserRepositoryImpl
import com.sivianes.kotlinkleankode.domain.interactor.PictureOfTheDayInteractor
import com.sivianes.kotlinkleankode.domain.interactor.PictureOfTheDayInteractorImpl
import com.sivianes.kotlinkleankode.domain.interactor.UserInteractor
import com.sivianes.kotlinkleankode.domain.interactor.UserInteractorImpl
import com.sivianes.kotlinkleankode.ui.main.MainPresenter
import com.sivianes.kotlinkleankode.ui.splash.SplashPresenter
import org.koin.dsl.module.module

val repositoryModule = module {
    // single instance of HelloRepository
    single<UserInteractor> { UserInteractorImpl(UserRepositoryImpl()) }
    // Simple Presenter Factory
    factory { SplashPresenter(get())}
}

val interactorModule = module {
    single<UserRepository> { UserRepositoryImpl() }
    factory { UserInteractorImpl(UserRepositoryImpl()) }
}

val pictureOfTheDayModule = module {
    single<PictureOfTheDayInteractor> { PictureOfTheDayInteractorImpl() }
    factory { MainPresenter(get()) }
}