package com.kotlin.zmvvm.base.repository

import com.kotlin.zmvvm.network.ApiService
import com.kotlin.zmvvm.network.RetrofitFactory


/**
 * Created by zhgq on 2020/6/15
 * Describe：获取接口文件
 */
abstract class ApiRepository : BaseRepository() {
    protected val apiService: ApiService by lazy {
        RetrofitFactory.instance.create(ApiService ::class.java)
    }


}