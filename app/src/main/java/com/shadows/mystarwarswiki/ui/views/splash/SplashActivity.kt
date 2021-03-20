package com.shadows.mystarwarswiki.ui.views.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.shadows.mystarwarswiki.databinding.ActivitySplashBinding
import com.shadows.mystarwarswiki.ui.views.binding.viewBinding
import com.shadows.mystarwarswiki.ui.views.search.SearchActivity

class SplashActivity: AppCompatActivity() {

    private val binding by viewBinding(ActivitySplashBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        goToSearchActivity()
    }
    private fun goToSearchActivity(){

        Handler().postDelayed({
            val intent = Intent(this, SearchActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
        },2500)
    }
}