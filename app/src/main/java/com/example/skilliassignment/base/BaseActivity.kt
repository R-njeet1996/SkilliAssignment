package com.app.base

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.example.skilliassignment.R


open class BaseActivity : AppCompatActivity() {

    private var alertDialog: Dialog? = null
    private var mToast: Toast? = null
    private var mProgressDialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        //        checkForCrashes();
    }


    fun launchActivity(activityClass: Class<out BaseActivity>) {
        startActivity(Intent(this, activityClass))
    }

    fun hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showProgressBar(msg: String) {
        if (alertDialog != null && alertDialog!!.isShowing && this.isFinishing) {
            return
        }
        alertDialog = Dialog(this)
        alertDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertDialog!!.setCancelable(true)
        alertDialog!!.show()
    }

    fun hideProgressBar() {
        if (alertDialog != null && alertDialog!!.isShowing) {
            alertDialog!!.dismiss()
            alertDialog = null
        }
    }

    fun showMsgToast(@StringRes resId: Int) {
        if (mToast != null) {
            mToast?.cancel()
            mToast = null
        }
        mToast = Toast.makeText(this, resId, Toast.LENGTH_SHORT)
        mToast?.show()
    }

    fun showMsgToast(msg: String) {
        if (mToast != null) {
            mToast?.cancel()
            mToast = null
        }
        mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT)
        mToast?.show()
    }
    fun showProgressDialog(context: Context?) {
        try {
            if (isDialogShowing()) {
                dismissProgressDialog()
            }

            if (context is Activity) {
                val activity = context as Activity?
                if (activity!!.isFinishing) {
                    return
                }
            }

            mProgressDialog = ProgressDialog(context, R.style.DialogTheme)
            if (context != null) {
                mProgressDialog?.show()
                val layoutParams = mProgressDialog?.getWindow()!!.getAttributes()
                layoutParams.dimAmount = 0.5f
                mProgressDialog?.getWindow()!!.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
                mProgressDialog?.setCancelable(false)
                mProgressDialog?.setContentView(R.layout.dialog_loading)
                val frameLayout = mProgressDialog?.findViewById(R.id.dlgProgress) as RelativeLayout

                (mProgressDialog?.findViewById(R.id.progress_wheel) as ProgressBar).progress = 10

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun dismissProgressDialog() {
        mProgressDialog?.dismiss()
        mProgressDialog = null
    }


    fun isDialogShowing(): Boolean {
        try {
            return if (mProgressDialog == null) {
                false
            } else {
                mProgressDialog!!.isShowing()
            }
        } catch (e: Exception) {
            return false
        }

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
