package com.shadows.core.utils

import com.shadows.core.R
import retrofit2.HttpException
import java.io.IOException

private const val HTTP_CODE_400_START = 400
private const val HTTP_CODE_400_END = 499
private const val HTTP_CODE_500_START = 500
private const val HTTP_CODE_500_END = 599

fun Throwable.getPersonalizedMessage(): NetworkThrowableHandler{
    return when(this){
        is HttpException -> {
            this.getMessage()
        }
        is IOException -> {
            NetworkThrowableHandler(R.string.error_no_connectivity_message)
        }
        else ->{
            NetworkThrowableHandler(R.string.error_unexpected_message)
        }
    }

}


fun HttpException.getMessage():  NetworkThrowableHandler{
    return when (this.code()) {
        in HTTP_CODE_400_START..HTTP_CODE_400_END -> {
            NetworkThrowableHandler(R.string.error_client_message)
        }
        in HTTP_CODE_500_START..HTTP_CODE_500_END -> {
            NetworkThrowableHandler(R.string.error_server_message)
        }
        else -> {
            NetworkThrowableHandler(R.string.error_unexpected_message)
        }
    }
}
 data class NetworkThrowableHandler(val stringResource: Int)