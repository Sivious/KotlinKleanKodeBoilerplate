package com.sivianes.kotlinkleankode.domain.interactor

import com.sivianes.kotlinkleankode.domain.model.POTD

interface PictureOfTheDayInteractor {
    fun getPOTDData(date : String)
    fun onCreate(callback: PictureOfTheDayInteractor.Callback)
    fun onPause()

    interface Callback {
        fun loadPictureData(pictureData : POTD)
        fun showError(error : String?)
    }
}