package com.kotlin.zmvvm.network

import com.kotlin.zmvvm.network.response.BaseResponse
import com.kotlin.zmvvm.network.response.EmptyResponse
import com.kotlin.zmvvm.ui.data.LoginResponse
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by zhgq on 2020/6/15
 * Describe：
 */
interface ApiService {

    /**
     * 使用协程+retrofit
     * 需要 suspend关键字
     */
    @POST("/user/login")
    suspend fun onLoginCo(
        @Query("username") username: String,
        @Query("password") password: String
    ): BaseResponse<LoginResponse>
}