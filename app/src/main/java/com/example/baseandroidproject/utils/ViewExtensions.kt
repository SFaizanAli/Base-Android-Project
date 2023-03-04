package com.example.baseandroidproject.utils

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String) {
    Glide.with(context)
        .load(url)
        .into(this)
}

fun View.visible() {
    if (visibility == View.GONE || visibility == View.INVISIBLE) {
        visibility = View.VISIBLE
    }
}

fun View.gone() {
    if (visibility == View.VISIBLE) {
        visibility = View.GONE
    }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

fun Activity.toast(message: String, toastLength: Int = 1) {
    Toast.makeText(this, message, toastLength)
        .show()
}