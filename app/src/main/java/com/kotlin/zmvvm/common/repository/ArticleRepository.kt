package com.kotlin.zmvvm.common.repository

import androidx.lifecycle.MutableLiveData
import com.kotlin.zmvvm.base.repository.ApiRepository
import com.kotlin.zmvvm.common.state.State
import com.kotlin.zmvvm.network.dataConvert
import com.kotlin.zmvvm.network.response.EmptyResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by zhgq on 2020/7/16
 * Describe：列表数据
 */
abstract class ArticleRepository(val loadState: MutableLiveData<State>) : ApiRepository() {


    suspend fun collectCo(id : Int ) :EmptyResponse {
        return withContext(Dispatchers.IO) {
            apiService.collect(id).dataConvert(loadState)
        }
    }

    suspend fun unCollectCo(id : Int ) :EmptyResponse {
        return withContext(Dispatchers.IO) {
            apiService.unCollect(id).dataConvert(loadState)
        }
    }
}