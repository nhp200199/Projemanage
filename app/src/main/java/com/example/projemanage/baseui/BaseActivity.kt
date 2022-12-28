package com.example.baseproject.baseui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.example.baseproject.dialog.LoadingDialog
import com.example.projemanage.R
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity<T: ViewBinding>: AppCompatActivity() {
    protected lateinit var binding: T

    private var dialog: DialogFragment? = null

    protected abstract fun getTag(): String

    protected abstract fun getViewBindingClass(inflater: LayoutInflater): T

    protected abstract fun setupView()

    protected abstract fun setViewListener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(getTag(), "onCreate()")
        binding = getViewBindingClass(layoutInflater)
        setContentView(binding.root)

        setupView()
        setViewListener()
    }

    override fun onStart() {
        super.onStart()
        Log.i(getTag(), "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.i(getTag(), "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.i(getTag(), "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.i(getTag(), "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(getTag(), "onDestroy()")
    }

    fun showToast(msg: String, toastLength: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, msg, toastLength).show()
    }

    fun showSnackBar(msg: String,
                     snackBarLength: Int = BaseTransientBottomBar.LENGTH_SHORT,
                     actionTxt: String? = null,
                     actionListener: View.OnClickListener? = null
    ) {
        val view = findViewById<View>(android.R.id.content).rootView
        val snackBar = Snackbar.make(view, msg, snackBarLength)
        if (actionTxt != null && actionListener != null) {
            snackBar.setAction(actionTxt, actionListener)
        }
        snackBar.show()
    }

    fun showLoadingDialog() {
        dialog = LoadingDialog(R.layout.dialog_loading)
        dialog!!.show(supportFragmentManager, "loading dialog")
    }

    fun hideDialog() {
        dialog?.let {
            if (it.dialog != null
                && it.dialog!!.isShowing
                && !it.isRemoving) {
                it.dismiss()
            }
        }
        dialog = null
    }
}