package com.sivianes.kotlinkleankode.ui.fragments

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import java.util.*

class DataPickerFragment : DialogFragment() {
    lateinit var mCallback: Callback

    interface Callback {
        fun setDate(date: String)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(activity!!, AlertDialog.THEME_HOLO_DARK , dateSetListener, year, month, day)

        return datePickerDialog
    }

    fun setView(callback: Callback) {
        this.mCallback = callback
    }

    private val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, day ->

        mCallback.setDate(
            "" + view.year +
                    "-" + (view.month + 1) +
                    "-" + view.dayOfMonth
        )
    }
}