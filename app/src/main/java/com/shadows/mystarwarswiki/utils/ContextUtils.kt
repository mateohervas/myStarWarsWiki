package com.shadows.mystarwarswiki.utils

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.shadows.mystarwarswiki.R

//this function transforms a color resource into a color
fun Int.asColor(context:Context) = ContextCompat.getColor(context, this)

//this function transforms a drawable resource into a drawable
fun Int.asDrawable(context:Context) = ContextCompat.getDrawable(context, this)

//this function creates a generic short toast
fun Context.showShortToast(message : String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

//this function creates a generic dialog to show different errors in the app and lets the user retry the operation
fun Context.showDialog(message:String, retry:() ->Unit) {
    AlertDialog.Builder(this).create().apply {
        setTitle(getString(R.string.Error))
        setMessage(message)
        setButton(AlertDialog.BUTTON_POSITIVE,getString(R.string.OK)) { _, _ -> dismiss() }
        setButton(AlertDialog.BUTTON_NEUTRAL,getString(R.string.Retry)){ _, _ ->
            retry()
            dismiss()
        }
    }.show()
}
