package com.kotlin.zmvvm.ui.project.repository

import androidx.lifecycle.MutableLiveData
import com.kotlin.zmvvm.common.repository.ArticleRepository
import com.kotlin.zmvvm.common.state.State
import com.kotlin.zmvvm.network.dataConvert
import com.kotlin.zmvvm.ui.project.data.ProjectBean
import com.kotlin.zmvvm.ui.project.data.ProjectTabBean

/**
 * Created by zhgq on 2020/9/9
 * Describe：获取项目数据 通过接口
 * @author 13718
 */
class ProjectRepository(loadState: MutableLiveData<State>) : ArticleRepository(loadState) {

    suspend fun loadProjectTabData():List<ProjectTabBean>{
        return apiService.loadProjectTabCo().dataConvert(loadState)
    }

    suspend fun loadProjectArticleData(page:Int,cid:Int):ProjectBean{
        return apiService.loadProjectArticlesCo(page,cid).dataConvert(loadState)
    }
}