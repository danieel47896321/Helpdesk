package com.example.helpdesk.myClass

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import com.example.helpdesk.R

class PopUpMSG {
    private var builder: AlertDialog.Builder
    constructor(context: Context, title: String?, message: String?) {
        builder = AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(context.resources.getString(R.string.OK)){ _, _ -> }
        builder.setCancelable(false)
        builder.create().show()
    }
    constructor(context: Context, title: String?, message: String?, destination: Class<*>?) {
        builder = AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(context.resources.getString(R.string.OK)) { _, _ ->
            context.startActivity(Intent(context, destination))
            (context as Activity).finish()
        }
        builder.setCancelable(false)
        builder.create().show()
    }
}