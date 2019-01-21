package com.sivianes.kotlinkleankode.ui.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sivianes.kotlinkleankode.R
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {

    private val splashPresenter: SplashPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val s : String = splashPresenter.getUserId()

        splashPresenter.setView(this)

        splashPresenter.navigateToMain()
    }
}