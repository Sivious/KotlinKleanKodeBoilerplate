package com.sivianes.kotlinkleankode.ui.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.sivianes.kotlinkleankode.domain.model.POTD
import com.sivianes.kotlinkleankode.ui.fragments.DataPickerFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_loading_screen.*
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity(), MainPresenter.View, View.OnClickListener, DataPickerFragment.Callback {
    private val mainPresenter: MainPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.sivianes.kotlinkleankode.R.layout.activity_main)
        mainPresenter.onCreate(this, this)
    }

    override fun initView() {
        startLoading()
        mainPresenter.beginSearch()
        tv_main_date.setOnClickListener { showDatePicker(rl_main_container) }
        rl_main_container.setOnClickListener {
            tv_main_photo_title.visibility = mainPresenter.getVisibility(tv_main_photo_title)
            sv_main_explanation.visibility = mainPresenter.getVisibility(sv_main_explanation)
            tv_main_explanation.visibility = mainPresenter.getVisibility(tv_main_explanation)
            tv_main_date.visibility = mainPresenter.getVisibility(tv_main_date)
        }
    }

    override fun onPause() {
        super.onPause()
        mainPresenter.onPause()
    }

    override fun showError(message: String?) {
        stopLoading()

        Snackbar.make(
            rl_main_container,
            getString(com.sivianes.kotlinkleankode.R.string.error_loading),
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction(getString(com.sivianes.kotlinkleankode.R.string.error_retry), this)
            .show() // Finally show the snack bar
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(com.sivianes.kotlinkleankode.R.menu.download_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            com.sivianes.kotlinkleankode.R.id.action_download -> {
                downloadImage()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode === 0) {
            var index = 0
            val permissionsMap = HashMap<String, Int>()
            for (permission in permissions) {
                permissionsMap.put(permission, grantResults[index])
                index++
            }

            if (permissionsMap.containsKey(Manifest.permission.WRITE_EXTERNAL_STORAGE) && permissionsMap.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == 0) {
                mainPresenter.downloadImage()
            }
        }
    }

    private fun downloadImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this!!, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0);
            } else {
                mainPresenter.downloadImage()
            }
        }
    }

    override fun loadPictureData(pictureData: POTD) {
        tv_main_photo_title.text = pictureData.title
        tv_main_explanation.text = pictureData.explanation
        tv_main_date.text = pictureData.date

        Picasso.with(applicationContext).load(pictureData.url)
            .placeholder(com.sivianes.kotlinkleankode.R.drawable.ic_image_placeholder)
            .into(iv_main_photo_background)

        stopLoading()
    }

    override fun onClick(p0: View?) {
        startLoading()
        mainPresenter.beginSearch()
    }

    private fun showDatePicker(v: View) {
        val newFragment = DataPickerFragment()
        newFragment.setView(this)
        newFragment.show(supportFragmentManager, getString(com.sivianes.kotlinkleankode.R.string.message_select_date))
    }

    override fun setDate(date: String) {
        mainPresenter.searchByDate(date)
    }

    private fun startLoading() {
        rl_loading_container.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        rl_loading_container.visibility = View.GONE
    }
}