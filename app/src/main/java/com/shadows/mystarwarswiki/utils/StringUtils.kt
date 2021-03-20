package com.shadows.mystarwarswiki.utils

//this method will check the url attributes and put them in a https format
fun String.toHttps(): String {
    // Default to https
    val url = this
    return if (url.contains("http")) {
        url.replace("http", "https")
    } else url
}