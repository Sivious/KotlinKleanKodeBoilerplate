package com.sivianes.kotlinkleankode.ui.splash

import android.app.Activity
import com.sivianes.kotlinkleankode.app.navigator.Navigator
import com.sivianes.kotlinkleankode.domain.interactor.UserInteractor

class SplashPresenter(private val userInteractor: UserInteractor) {
    lateinit var mView : Activity

    fun getUserId() = userInteractor.getUserId()

    fun setView(activity: SplashActivity) {
        this.mView = activity
    }

    fun navigateToMain () = Navigator.navigateToMainActivity(mView)
}