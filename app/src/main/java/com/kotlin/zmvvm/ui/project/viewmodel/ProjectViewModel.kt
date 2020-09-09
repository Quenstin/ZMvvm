package com.kotlin.zmvvm.ui.project.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kotlin.zmvvm.common.viewmodel.ArticleViewModel
import com.kotlin.zmvvm.ui.project.data.ProjectBean
import com.kotlin.zmvvm.ui.project.data.ProjectTabBean
import com.kotlin.zmvvm.ui.project.repository.ProjectRepository
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * Created by zhgq on 2020/9/9
 * Describe：
 * @author 13718
 */
class ProjectViewModel(application: Application) : ArticleViewModel<ProjectRepository>(application) {

    val mProjectTabData:MutableLiveData<List<ProjectTabBean>> = MutableLiveData()

    val mProjectData:MutableLiveData<ProjectBean> = MutableLiveData()

    fun getProjectTabData(){
        viewModelScope.launch {
            try {
               mProjectTabData.value=mRepository.loadProjectTabData()
            }catch (e:Exception){

            }
        }
    }

    fun getProjectData(page:Int,cid:Int){
        viewModelScope.launch {
            try {
                mProjectData.value=mRepository.loadProjectArticleData(page,cid)
            }catch (e:Exception){

            }
        }
    }
}