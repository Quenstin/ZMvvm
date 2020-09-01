package com.kotlin.zmvvm.ui.system.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kotlin.zmvvm.common.viewmodel.ArticleViewModel
import com.kotlin.zmvvm.ui.system.data.SystemArticleResponse
import com.kotlin.zmvvm.ui.system.data.SystemTabNameResponse
import com.kotlin.zmvvm.ui.system.repository.SystemRepository
import kotlinx.coroutines.launch

/**
 * Created by zhgq on 2020/8/28
 * Describe：
 */
class SystemViewModel(application: Application) : ArticleViewModel<SystemRepository>(application) {

    val systemTabData : MutableLiveData<List<SystemTabNameResponse>> =MutableLiveData()

    val systemListData: MutableLiveData<SystemArticleResponse> =MutableLiveData()


    fun loadSystemTab(){
        viewModelScope.launch {
            try {
               systemTabData.value= mRepository.loadSystemData()
            }catch (e:Exception){

            }
        }
    }

    fun loadSystemList(pag:Int,cid:Int?){
        viewModelScope.launch {
            try {
                systemListData.value=mRepository.loadSystemListData(pag, cid)
            }catch (e:Exception){

            }
        }
    }

}