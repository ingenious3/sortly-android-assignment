package com.example.sortly.data.datasource.network

import com.example.sortly.data.datasource.network.NetworkConstants.DEFAULT_ERROR

sealed class ResultData<out T : Any> {
    class Loading(): ResultData<Nothing>()
    class Success<out T : Any>(val data: T) : ResultData<T>()
    class Failure(val errorCode: Int, val msg: String = DEFAULT_ERROR) : ResultData<Nothing>()
    class Exception(val throwable: Throwable?) : ResultData<Nothing>()
}