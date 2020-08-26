package com.kotlin.zmvvm.ui.wechat.repository

import androidx.lifecycle.MutableLiveData
import com.kotlin.zmvvm.common.repository.ArticleRepository
import com.kotlin.zmvvm.common.state.State
import com.kotlin.zmvvm.network.dataConvert
import com.kotlin.zmvvm.ui.wechat.data.WeChatArticleResponse
import com.kotlin.zmvvm.ui.wechat.data.WeChatTabNameResponse

/**
 * Created by zhgq on 2020/8/24
 * Describe：
 */
class WeChatRepository(loadState: MutableLiveData<State>) : ArticleRepository(loadState) {

    suspend fun getWeChatArticleData(cid:Int,page:Int):WeChatArticleResponse{
        return apiService.loadWeChatArticlesCo(cid, page).dataConvert(loadState)

    }

    suspend fun getWeChatTabStr():List<WeChatTabNameResponse>{
        return apiService.loadWeChatTabCo().dataConvert(loadState)
    }

}