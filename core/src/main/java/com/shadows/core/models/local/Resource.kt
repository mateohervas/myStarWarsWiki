package com.shadows.core.models.local

import com.shadows.core.utils.NetworkThrowableHandler

//this sealed class will let us handle states in the ViewModel

sealed class Resource<out T>{
    data class Success<out T>(val data: T): Resource<T>()
    data class Error<out T>(val error: NetworkThrowableHandler): Resource<T>()
    class Loading<out T>(val isLoading: Boolean) : Resource<T>()
}
