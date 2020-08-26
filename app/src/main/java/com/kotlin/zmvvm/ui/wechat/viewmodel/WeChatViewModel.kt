package com.kotlin.zmvvm.ui.wechat.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kotlin.zmvvm.base.viewmodel.BaseViewModel
import com.kotlin.zmvvm.common.viewmodel.ArticleViewModel
import com.kotlin.zmvvm.ui.wechat.data.WeChatArticleResponse
import com.kotlin.zmvvm.ui.wechat.data.WeChatTabNameResponse
import com.kotlin.zmvvm.ui.wechat.repository.WeChatRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception

/**
 * Created by zhgq on 2020/8/24
 * Describe：
 */
class WeChatViewModel(application: Application) : ArticleViewModel<WeChatRepository>(application) {

     val mWeChatData: MutableLiveData<WeChatArticleResponse> = MutableLiveData()

     val mWeChatTabData:MutableLiveData<List<WeChatTabNameResponse>> = MutableLiveData()

    fun getWeChatTabNameStr(){
        viewModelScope.launch {
            try {
                mWeChatTabData.value=mRepository.getWeChatTabStr()
            }catch (e:Exception){

            }
        }
    }

    fun getWeChatArticleData(cid:Int,page:Int){
        viewModelScope.launch {
          try {
              mWeChatData.value=mRepository.getWeChatArticleData(cid,page)

          }catch (e:Exception){

          }
        }
    }
}