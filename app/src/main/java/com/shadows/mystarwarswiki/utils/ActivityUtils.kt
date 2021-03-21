package com.shadows.mystarwarswiki.utils

import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity



fun AppCompatActivity.blockTouch(flag:Boolean){
    if(flag){
        window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }else{
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
}

