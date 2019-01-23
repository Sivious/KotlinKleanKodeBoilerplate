package com.sivianes.kotlinkleankode.ui.main

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.sivianes.kotlinkleankode.domain.model.POTD
import com.sivianes.kotlinkleankode.ui.fragments.DataPickerFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MainPresenter.View, View.OnClickListener, DataPickerFragment.Callback {
    private val mainPresenter: MainPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.sivianes.kotlinkleankode.R.layout.activity_main)
        mainPresenter.onCreate(this)
    }

    override fun initView() {
        mainPresenter.beginSearch()
        tv_main_date.setOnClickListener { showDatePicker(rl_main_container) }
        rl_main_container.setOnClickListener {
            tv_main_photo_title.visibility = mainPresenter.getVisibility(tv_main_photo_title)
            tv_main_explanation.visibility = mainPresenter.getVisibility(tv_main_explanation)
            tv_main_date.visibility = mainPresenter.getVisibility(tv_main_date)
        }
    }

    override fun onPause() {
        super.onPause()
        mainPresenter.onPause()
    }

    override fun showError(message: String?) {
        Snackbar.make(
            rl_main_container,
            getString(com.sivianes.kotlinkleankode.R.string.error_loading),
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction(getString(com.sivianes.kotlinkleankode.R.string.error_retry), this)
            .show() // Finally show the snack bar
    }

    override fun loadPictureData(pictureData: POTD) {
        tv_main_photo_title.text = pictureData.title
        tv_main_explanation.text = pictureData.explanation
        tv_main_date.text = pictureData.date

        Picasso.with(applicationContext).load(pictureData.url)
            .placeholder(com.sivianes.kotlinkleankode.R.drawable.ic_image_placeholder)
            .into(iv_main_photo_background)
    }

    override fun onClick(p0: View?) {
        mainPresenter.beginSearch()
    }

    private fun showDatePicker(v: View) {
        val newFragment = DataPickerFragment()
        newFragment.setView(this)
        newFragment.show(supportFragmentManager, "Select Date")
    }

    override fun setDate(date: String) {
        mainPresenter.searchByDate(date)
    }
}