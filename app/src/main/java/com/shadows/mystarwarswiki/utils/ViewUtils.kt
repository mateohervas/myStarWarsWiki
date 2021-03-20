package com.shadows.mystarwarswiki.utils

import android.view.View

fun View.show(flag:Boolean){
    if(flag){
        this.visibility = View.VISIBLE
    }else{
        this.visibility = View.GONE
    }
}