package com.example.footballleagues.common

import android.app.AlertDialog
import android.content.Context

object AlertMessages {

    fun showErrorDialog(context: Context, message: String) {
        val builder = AlertDialog.Builder(context)
            .setTitle("Error")
            .setMessage(message)
            .setPositiveButton(android.R.string.ok) { d, _ ->
                d.dismiss()
            }.create()
        builder.show()
    }
}