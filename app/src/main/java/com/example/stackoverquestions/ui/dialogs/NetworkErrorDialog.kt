package com.example.stackoverquestions.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import com.example.stackoverquestions.networkutil.Constants.NETWORK_UNAVAILABLE_DESC
import com.example.stackoverquestions.networkutil.Constants.NETWORK_UNAVAILABLE_TITLE

class NetworkErrorDialog(context: Context): AlertDialog.Builder(context) {

    override fun setTitle(title: CharSequence?): AlertDialog.Builder {
        return super.setTitle(NETWORK_UNAVAILABLE_TITLE)
    }

    override fun setMessage(message: CharSequence?): AlertDialog.Builder {
        return super.setMessage(NETWORK_UNAVAILABLE_DESC)
    }

    override fun setCancelable(cancelable: Boolean): AlertDialog.Builder {
        return super.setCancelable(false)
    }
}