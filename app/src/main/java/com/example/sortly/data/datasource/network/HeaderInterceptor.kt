package com.example.sortly.data.datasource.network

import com.example.sortly.data.datasource.network.NetworkConstants.AUTHORIZATION_KEY
import com.example.sortly.data.datasource.network.NetworkConstants.AUTHORIZATION_VALUE
import com.example.sortly.data.datasource.network.NetworkConstants.HOST_KEY
import com.example.sortly.data.datasource.network.NetworkConstants.HOST_VALUE
import com.example.sortly.data.datasource.network.NetworkConstants.REQUEST_NAME_KEY
import com.example.sortly.data.datasource.network.NetworkConstants.REQUEST_NAME_VALUE
import com.example.sortly.data.datasource.network.NetworkConstants.USER_AGENT_KEY
import com.example.sortly.data.datasource.network.NetworkConstants.USER_AGENT_VALUE
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .header(REQUEST_NAME_KEY, REQUEST_NAME_VALUE)
            .header(AUTHORIZATION_KEY, AUTHORIZATION_VALUE )
            .header(USER_AGENT_KEY, USER_AGENT_VALUE)
            .header(HOST_KEY, HOST_VALUE)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}