package com.kotlin.zmvvm.network

import com.kotlin.zmvvm.network.response.BaseResponse
import com.kotlin.zmvvm.network.response.EmptyResponse
import com.kotlin.zmvvm.ui.data.LoginResponse
import com.kotlin.zmvvm.ui.common.data.ArticleBean
import com.kotlin.zmvvm.ui.home.data.BannerResponseBean
import com.kotlin.zmvvm.ui.home.data.HomeArticleResponseBean
import com.kotlin.zmvvm.ui.navigation.data.NavgationBean
import com.kotlin.zmvvm.ui.system.data.SystemArticleResponse
import com.kotlin.zmvvm.ui.system.data.SystemTabNameResponse
import com.kotlin.zmvvm.ui.wechat.data.WeChatArticleResponse
import com.kotlin.zmvvm.ui.wechat.data.WeChatTabNameResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by zhgq on 2020/6/15
 * Describe：接口列表
 */
interface ApiService {

    /**
     * 使用协程+retrofit
     * 需要 suspend关键字
     */
    @POST("/user/login")
    suspend fun onLoginCo(
        @Query("username") username: String,
        @Query("password") password: String
    ): BaseResponse<LoginResponse>

    @POST("/lg/collect/{id}/json" )
    suspend fun collect(@Path("id")id:Int):BaseResponse<EmptyResponse>

    @POST("/lg/uncollect_originId/{id}/json")
    suspend fun unCollect(@Path("id") id: Int): BaseResponse<EmptyResponse>

    @GET("/banner/json")
    suspend fun loadBannerCo() : BaseResponse<List<BannerResponseBean>>

    @GET("/article/top/json")
    suspend fun loadTopArticleCo(): BaseResponse<List<ArticleBean>>

    @GET("/article/list/{pageNum}/json")
    suspend fun loadHomeArticleCo(@Path("pageNum") pageNum: Int): BaseResponse<HomeArticleResponseBean>


    @GET("/wxarticle/chapters/json")
    suspend fun loadWeChatTabCo(): BaseResponse<List<WeChatTabNameResponse>>

    @GET("/wxarticle/list/{cid}/{pageNum}/json")
    suspend fun loadWeChatArticlesCo(@Path("cid") cid: Int, @Path("pageNum") page: Int)
            : BaseResponse<WeChatArticleResponse>


    @GET("/tree/json")
    suspend fun loadSystemTabCo(): BaseResponse<List<SystemTabNameResponse>>

    @GET("/article/list/{pageNum}/json")
    suspend fun loadSystemArticlesCo(
        @Path("pageNum") pageNum: Int,
        @Query("cid") id: Int?
    ): BaseResponse<SystemArticleResponse>

    @GET("/navi/json")
    suspend fun loadNavigationTabCo(): BaseResponse<List<NavgationBean>>



}