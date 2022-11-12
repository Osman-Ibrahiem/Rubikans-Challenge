package com.rubikans.challenge.extension

import android.app.Activity
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.rubikans.challenge.R

internal fun Activity.showSnackBar(view: View, message: String, isError: Boolean = false) {
    val sb = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
    if (isError)
        sb.setBackgroundTint(loadColor(R.color.color_error))
            .setTextColor(loadColor(R.color.white))
            .show()
    else
        sb.setBackgroundTint(loadColor(R.color.color_info))
            .setTextColor(loadColor(R.color.black))
            .show()

}


internal fun Fragment.showSnackBar(view: View, message: String, isError: Boolean = false) {
    val sb = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
    if (isError)
        sb.setBackgroundTint(context!!.loadColor(R.color.color_error))
            .setTextColor(context!!.loadColor(R.color.white))
            .show()
    else
        sb.setBackgroundTint(context!!.loadColor(R.color.color_info))
            .setTextColor(context!!.loadColor(R.color.black))
            .show()

}

inline fun SearchView.onQueryTextChanged(crossinline listener: (String) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            listener(newText.orEmpty())
            return true
        }
    })
}