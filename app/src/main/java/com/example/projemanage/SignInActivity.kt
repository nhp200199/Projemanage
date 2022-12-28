package com.example.projemanage

import android.content.Intent
import android.text.TextUtils
import android.view.LayoutInflater
import com.example.baseproject.baseui.BaseActivity
import com.example.projemanage.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : BaseActivity<ActivitySignInBinding>() {
    override fun getTag(): String {
        return SignInActivity::class.java.simpleName
    }

    override fun getViewBindingClass(inflater: LayoutInflater): ActivitySignInBinding {
        return ActivitySignInBinding.inflate(inflater)
    }

    override fun setupView() {
        setSupportActionBar(binding.toolbar)
        val actionBar = supportActionBar
        actionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24)
        }
    }

    override fun setViewListener() {
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        binding.btnSignIn.setOnClickListener {
            val email = binding.edtEmail.text.trim().toString()
            val password = binding.edtPassword.text.trim().toString()

            if (validatedUserInput(email, password)) {
                showLoadingDialog()
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        hideDialog()
                        if (task.isSuccessful) {
                            startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                            //TODO: clear activity when successfully logged in
                        } else {
                            showToast("Error when logging in. ${task.exception?.toString()}")
                        }
                    }
            }

        }
    }

    private fun validatedUserInput(email: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(email) -> {
                showToast("empty email field")
                false
            }
            TextUtils.isEmpty(password) -> {
                showSnackBar("empty password field")
                false
            }
            else -> true
        }
    }

}