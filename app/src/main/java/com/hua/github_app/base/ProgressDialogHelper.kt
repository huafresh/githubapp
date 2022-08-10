package com.hua.github_app.base

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.hua.github_app.R

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
class ProgressDialogHelper(private val activity: FragmentActivity) {

    private var progressDialog: ProgressDialog? = null

    private fun getProgressDialog(): ProgressDialog {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(activity)
            progressDialog?.setCancelable(true)
        }
        return progressDialog!!
    }

    fun observeShowingDialog(vm: BaseViewModel) {
        vm.progressDialogConfig.observe(activity) { dialogConfig ->
            val dialog = getProgressDialog()
            val message = dialogConfig.message
            if (message != null) {
                dialog.setMessage(message)
            } else {
                dialog.setMessage(activity.getString(R.string.please_waiting))
            }
            if (dialogConfig.show) {
                dialog.show()
            } else {
                dialog.dismiss()
            }
        }
    }

    fun onDestroy() {
        progressDialog?.dismiss()
        progressDialog = null
    }

}