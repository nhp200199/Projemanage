package com.example.projemanage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.WindowManager
import com.example.baseproject.baseui.BaseActivity
import com.example.projemanage.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity<ActivityIntroBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    override fun getTag(): String {
        return IntroActivity::class.java.simpleName
    }

    override fun getViewBindingClass(inflater: LayoutInflater): ActivityIntroBinding {
        return ActivityIntroBinding.inflate(inflater)
    }

    override fun setupView() {
    }

    override fun setViewListener() {
        binding.btnSignUp.setOnClickListener {
            Log.d(getTag(), "on sign up click")
            startActivity(Intent(this@IntroActivity, SignUpActivity::class.java)) }

        binding.btnSignIn.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }
}