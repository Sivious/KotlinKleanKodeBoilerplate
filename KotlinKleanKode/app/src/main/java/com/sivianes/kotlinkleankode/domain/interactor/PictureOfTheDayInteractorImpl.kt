package com.sivianes.kotlinkleankode.domain.interactor

import com.sivianes.kotlinkleankode.domain.api.Api
import com.sivianes.kotlinkleankode.ui.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PictureOfTheDayInteractorImpl : PictureOfTheDayInteractor {

    lateinit var mCallback: PictureOfTheDayInteractor.Callback

    val api by lazy {
        Api.create()
    }
    var disposable: Disposable? = null

    override fun onCreate(callback: PictureOfTheDayInteractor.Callback) {
        this.mCallback = callback
    }

    override fun getPOTDData(date : String) {
        disposable =
                api.getPOTD(date, true, Constants.NASA_API_TOKEN)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { result -> mCallback.loadPictureData(result) },
                        { error -> mCallback.showError(error.message) }
                    )
    }

    override fun onPause() {
        disposable?.dispose()
    }
}