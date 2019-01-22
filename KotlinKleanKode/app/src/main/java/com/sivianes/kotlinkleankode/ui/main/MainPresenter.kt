package com.sivianes.kotlinkleankode.ui.main

import com.sivianes.kotlinkleankode.domain.interactor.PictureOfTheDayInteractor
import com.sivianes.kotlinkleankode.domain.model.POTD

class MainPresenter(private val potdInteractor: PictureOfTheDayInteractor) : PictureOfTheDayInteractor.Callback {
    lateinit var mView : View

    interface View {
        fun loadPictureData(pictureData : POTD)
        fun showError(error : String?)
        fun initView()
    }

    fun onCreate(view : View) {
        this.mView = view
        mView.initView()
        potdInteractor.onCreate(this)
    }

    fun onPause(){
        potdInteractor.onPause()
    }

    fun beginSearch() {
        potdInteractor.getPOTDData()
    }

    override fun loadPictureData(pictureData: POTD) {
        mView.loadPictureData(pictureData)
    }

    override fun showError(error: String?) {
        mView.showError(error)
    }

}