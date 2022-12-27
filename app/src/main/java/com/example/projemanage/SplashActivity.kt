package com.example.projemanage

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import com.example.baseproject.baseui.BaseActivity
import com.example.projemanage.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    override fun getTag(): String {
        return SplashActivity::class.java.simpleName
    }

    override fun getViewBindingClass(inflater: LayoutInflater): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(inflater)
    }

    override fun setupView() {
        val typeFace: Typeface = Typeface.createFromAsset(assets, "carbon bl.ttf")
        binding.textView.typeface = typeFace
    }

    override fun setViewListener() {
    }
}