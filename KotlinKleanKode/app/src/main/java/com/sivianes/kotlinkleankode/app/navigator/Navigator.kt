package com.sivianes.kotlinkleankode.app.navigator

import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import com.sivianes.kotlinkleankode.R
import com.sivianes.kotlinkleankode.ui.main.MainActivity

interface Navigator {
    companion object {

        fun navigateToMainActivity(activity: Activity?) {
            if (activity != null) {
                val intent = Intent(activity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                val compat = ActivityOptionsCompat.makeCustomAnimation(activity, R.anim.fade_in, R.anim.fade_out)
                activity.startActivity(intent, compat.toBundle())
                activity.finish()
            }
        }
    }
}
