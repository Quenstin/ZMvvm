package com.kotlin.zmvvm.ui.repository

import androidx.lifecycle.MutableLiveData
import com.kotlin.zmvvm.base.repository.ApiRepository
import com.kotlin.zmvvm.common.state.State
import com.kotlin.zmvvm.network.dataConvert
import com.kotlin.zmvvm.ui.data.LoginResponse

/**
 * Created by zhgq on 2020/6/17
 * Describe：登陆res  使用协程+retorfit
 */
class LoginRepository(val loadState: MutableLiveData<State>) : ApiRepository() {

    suspend fun login(userName: String, password: String): LoginResponse {
        return apiService.onLoginCo(userName, password).dataConvert(loadState)
    }
}