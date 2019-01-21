package com.sivianes.kotlinkleankode.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.sivianes.kotlinkleankode.R
import com.sivianes.kotlinkleankode.domain.api.Api
import com.sivianes.kotlinkleankode.domain.model.POTD
import com.sivianes.kotlinkleankode.ui.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    val api by lazy {
        Api.create()
    }
    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        beginSearch("Meh")
    }


    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    private fun beginSearch(srsearch: String) {
        disposable =
                api.getPOTD("2019-01-02", true, Constants.NASA_API_TOKEN)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { result -> showResult(result) },
                        { error -> showError(error.message) }
                    )
    }

    private fun showError(message: String?) {
        Log.d("MAIN", message)
    }

    private fun showResult(result: POTD?) {
        Log.d("MAIN", POTD.toString())
    }
}