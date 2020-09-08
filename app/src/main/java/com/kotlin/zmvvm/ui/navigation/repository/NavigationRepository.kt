package com.kotlin.zmvvm.ui.navigation.repository

import androidx.lifecycle.MutableLiveData
import com.kotlin.zmvvm.common.repository.ArticleRepository
import com.kotlin.zmvvm.common.state.State
import com.kotlin.zmvvm.network.dataConvert
import com.kotlin.zmvvm.ui.navigation.data.NavgationBean

/**
 * Created by zhgq on 2020/9/8
 * Describe：
 * @author 13718
 */
class NavigationRepository(loadState: MutableLiveData<State>) : ArticleRepository(loadState) {

    suspend fun loadNavigationData():List<NavgationBean>{
        return apiService.loadNavigationTabCo().dataConvert(loadState)
    }
}