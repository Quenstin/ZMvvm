package com.kotlin.zmvvm.common.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kotlin.zmvvm.base.viewmodel.BaseViewModel
import com.kotlin.zmvvm.common.repository.ArticleRepository
import com.kotlin.zmvvm.network.response.EmptyResponse
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * Created by zhgq on 2020/7/16
 * Describe：列表使用的ArticleViewModel
 */

abstract class ArticleViewModel<T : ArticleRepository>(application: Application) :
    BaseViewModel<T>(application) {


    // 使用协程 + Retrofit2.6以上版本
    var mCollectData : MutableLiveData<EmptyResponse> = MutableLiveData()

    fun collectCo(id: Int) {
        viewModelScope.launch {
            try {
                mCollectData.value = mRepository.collectCo(id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun unCollectCo(id: Int) {
        viewModelScope.launch {
            try {
                mCollectData.value = mRepository.unCollectCo(id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}