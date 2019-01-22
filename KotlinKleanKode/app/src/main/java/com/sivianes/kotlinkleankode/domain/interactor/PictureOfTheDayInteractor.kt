package com.sivianes.kotlinkleankode.domain.interactor

import com.sivianes.kotlinkleankode.domain.model.POTD

interface PictureOfTheDayInteractor {
    fun getPOTDData()
    fun onCreate(callback: PictureOfTheDayInteractor.Callback)
    fun onPause()

    interface Callback {
        fun loadPictureData(pictureData : POTD)
        fun showError(error : String?)
    }
}