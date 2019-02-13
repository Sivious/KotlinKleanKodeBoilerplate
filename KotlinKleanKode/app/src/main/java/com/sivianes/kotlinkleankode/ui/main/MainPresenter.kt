package com.sivianes.kotlinkleankode.ui.main

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.view.View
import com.sivianes.kotlinkleankode.R
import com.sivianes.kotlinkleankode.domain.interactor.PictureOfTheDayInteractor
import com.sivianes.kotlinkleankode.domain.model.POTD
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class MainPresenter(private val potdInteractor: PictureOfTheDayInteractor) : PictureOfTheDayInteractor.Callback {
    lateinit var mView: View
    lateinit var mURL: String
    lateinit var mContext: Context

    interface View {
        fun loadPictureData(pictureData: POTD)
        fun showError(error: String?)
        fun initView()
    }

    fun onCreate(view: View, context: Context) {
        this.mContext = context
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
        this.mURL = pictureData.url
        mView.loadPictureData(pictureData)
    }

    override fun showError(error: String?) {
        mView.showError(error)
    }

    fun downloadImage() {
        //startDownload(mURL, getTempFile(mURL)!!.absolutePath)
        startDownload(mURL, getCacheFileName(mURL))
    }


    fun startDownload(downloadPath: String, destinationPath: String) {
        val uri = Uri.parse(downloadPath)
        val request = DownloadManager.Request(uri)

        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setTitle(mContext.getString(R.string.download_manager_title))
        request.setVisibleInDownloadsUi(true)
        request.setDestinationInExternalPublicDir(destinationPath, uri.lastPathSegment)

        (mContext.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager).enqueue(request)
    }

    private fun getTempFile(url: String): File? =

        Uri.parse(url)?.lastPathSegment?.let { filename ->
            File.createTempFile(filename, null, mContext.cacheDir)
        }

    private fun getCacheFileName (url : String) : String {
        return mContext.cacheDir.absolutePath + "/" + Uri.parse(url)?.lastPathSegment
    }

}