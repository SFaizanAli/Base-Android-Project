package com.example.baseandroidproject.utils


import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.Toast
import com.example.baseandroidproject.R


object Common {

    var STAGING_BASE_URL = "http://172.16.201.209/testing-app/public/"
    var dialogTitle = "Are you sure?"
    var dialogMessage = "Do you want to delete this"
    var somethingwentwrong = "something went wrong"


    var internetConnection: Boolean? = null
    fun toastMessageShow(context: Context?, message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }



    fun loadingDialog(context: Context?): Dialog {
        val loadingDialog = Dialog(context!!)
        loadingDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        loadingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        loadingDialog.setContentView(R.layout.loading)
        loadingDialog.setCanceledOnTouchOutside(false)
        return loadingDialog
    }

}