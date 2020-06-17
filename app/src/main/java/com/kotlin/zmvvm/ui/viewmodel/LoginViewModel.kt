package com.kotlin.zmvvm.ui.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kotlin.zmvvm.base.viewmodel.BaseViewModel
import com.kotlin.zmvvm.ui.data.LoginResponse
import com.kotlin.zmvvm.ui.repository.LoginRepository
import kotlinx.coroutines.launch

/**
 * Created by zhgq on 2020/6/17
 * Describe：
 */
class LoginViewModel(application: Application) : BaseViewModel<LoginRepository>(application) {

    val loginData : MutableLiveData<LoginResponse> = MutableLiveData()

    fun getLoginData(userName:String,passWord:String){
        viewModelScope.launch {
        loginData.value=mRepository.login(userName,passWord)
        }
    }
}