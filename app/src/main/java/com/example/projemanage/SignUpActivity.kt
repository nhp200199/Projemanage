package com.example.projemanage

import android.text.TextUtils
import android.view.LayoutInflater
import com.example.baseproject.baseui.BaseActivity
import com.example.projemanage.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : BaseActivity<ActivitySignUpBinding>() {
    override fun getTag(): String {
        return SignUpActivity::class.java.simpleName
    }

    override fun getViewBindingClass(inflater: LayoutInflater): ActivitySignUpBinding {
        return ActivitySignUpBinding.inflate(inflater)
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

        binding.btnSignUp.setOnClickListener {
            val name = binding.edtName.text.trim { c -> c == ' '}.toString()
            val email = binding.edtEmail.text.trim { c -> c == ' '}.toString()
            val password = binding.edtPassword.text.trim { c -> c == ' '}.toString()
            if (validatedUserInput(name, email, password))
                signUpAccount(name, email, password)
        }
    }

    private fun validatedUserInput(name: String, email: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(name) -> {
                showToast("empty name field")
                false
            }
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

    private fun signUpAccount(name: String, email: String, password: String) {
        showLoadingDialog()
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                hideDialog()
                if (task.isSuccessful) {
                    val firebaseUser = task.result.user!!
                    val registeredEmail = firebaseUser.email!!
                    showToast("Hi $name, you have registered successfully via $registeredEmail")
                } else {
                    showToast("error during signing up")
                }
            }
            .addOnFailureListener {
                showToast(it.message ?: "exception during signing up")
            }
    }
}