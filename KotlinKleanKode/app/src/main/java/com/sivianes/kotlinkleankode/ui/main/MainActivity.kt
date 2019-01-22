package com.sivianes.kotlinkleankode.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.sivianes.kotlinkleankode.R
import com.sivianes.kotlinkleankode.domain.model.POTD
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MainPresenter.View {
    private val mainPresenter: MainPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter.onCreate(this)
    }

    override fun initView() {
        mainPresenter.beginSearch()
    }

    override fun onPause() {
        super.onPause()
        mainPresenter.onPause()
    }

    override fun showError(message: String?) {
        Log.d("MAIN", message)
    }

    override fun loadPictureData(pictureData: POTD) {
        Log.d("MAIN", POTD.toString())
    }
}