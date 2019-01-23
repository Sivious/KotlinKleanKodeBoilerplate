package com.sivianes.kotlinkleankode.ui.main

import android.view.View
import com.sivianes.kotlinkleankode.domain.interactor.PictureOfTheDayInteractor
import com.sivianes.kotlinkleankode.domain.model.POTD
import java.text.SimpleDateFormat
import java.util.*


class MainPresenter(private val potdInteractor: PictureOfTheDayInteractor) : PictureOfTheDayInteractor.Callback {
    lateinit var mView: View

    interface View {
        fun loadPictureData(pictureData: POTD)
        fun showError(error: String?)
        fun initView()
    }

    fun onCreate(view: View) {
        this.mView = view
        mView.initView()
        potdInteractor.onCreate(this)
    }

    fun onPause() {
        potdInteractor.onPause()
    }

    fun beginSearch() {
        potdInteractor.getPOTDData(today())
    }

    fun searchByDate(date: String) {
        potdInteractor.getPOTDData(date)
    }

    fun getVisibility(view: android.view.View): Int {
        return if (view.visibility == android.view.View.VISIBLE) {
            android.view.View.GONE
        } else {
            android.view.View.VISIBLE
        }
    }

    private fun today(): String {
        val c = Calendar.getInstance().getTime()
        println("Current time => $c")

        val df = SimpleDateFormat("yyyy-MM-dd")
        val formattedDate = df.format(c)

        return formattedDate
    }

    override fun loadPictureData(pictureData: POTD) {
        mView.loadPictureData(pictureData)
    }

    override fun showError(error: String?) {
        mView.showError(error)
    }
}