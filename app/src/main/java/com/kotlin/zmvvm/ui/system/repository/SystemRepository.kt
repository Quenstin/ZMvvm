package com.kotlin.zmvvm.ui.system.repository

import androidx.lifecycle.MutableLiveData
import com.kotlin.zmvvm.base.repository.BaseRepository
import com.kotlin.zmvvm.common.repository.ArticleRepository
import com.kotlin.zmvvm.common.state.State
import com.kotlin.zmvvm.network.dataConvert
import com.kotlin.zmvvm.ui.system.data.SystemArticleResponse
import com.kotlin.zmvvm.ui.system.data.SystemTabNameResponse

/**
 * Created by zhgq on 2020/8/28
 * Describe：
 */
class SystemRepository(loadState: MutableLiveData<State>) : ArticleRepository(loadState) {

    suspend fun loadSystemData() : List<SystemTabNameResponse>{
        return apiService.loadSystemTabCo().dataConvert(loadState)

    }

    suspend fun loadSystemListData(pag:Int,cid:Int?):SystemArticleResponse{
        return apiService.loadSystemArticlesCo(pag,cid).dataConvert(loadState)
    }
}