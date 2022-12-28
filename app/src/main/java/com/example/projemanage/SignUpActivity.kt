package com.example.projemanage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.baseproject.baseui.BaseActivity
import com.example.projemanage.databinding.ActivitySignUpBinding

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
    }
}