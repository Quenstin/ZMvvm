package com.kotlin.zmvvm.ui.navigation.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kotlin.zmvvm.base.viewmodel.BaseViewModel
import com.kotlin.zmvvm.ui.navigation.data.NavgationBean
import com.kotlin.zmvvm.ui.navigation.repository.NavigationRepository
import kotlinx.coroutines.launch

/**
 * Created by zhgq on 2020/9/8
 * Describe：
 * @author 13718
 */
class NavigationViewModel(application: Application) : BaseViewModel<NavigationRepository>(application) {

    var navigationTab:MutableLiveData<List<NavgationBean>> = MutableLiveData()

    fun getNavigationData(){
        viewModelScope.launch {
            try {
                navigationTab.value=mRepository.loadNavigationData()

            }catch (e:Exception){

            }
        }
    }
}