package com.shadows.core.utils

import java.math.BigDecimal
import java.math.RoundingMode

fun String?.toInches(): String{
    return if(this.isNullOrEmpty()){
        "0"
    }else{
        BigDecimal(this.toDouble()*0.393701)
            .setScale(2,RoundingMode.HALF_EVEN).toString()
    }
}