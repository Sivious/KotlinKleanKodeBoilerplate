package com.sivianes.kotlinkleankode.ui.splash

import com.sivianes.kotlinkleankode.domain.interactor.UserInteractor

class SplashPresenter(private val userInteractor: UserInteractor) {
    fun getUserId() = userInteractor.getUserId()
}