package com.kotlin.zmvvm.network

import com.kotlin.zmvvm.common.utils.Constant
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

/**
 * Created by zhgq on 2020/6/15
 * Describe：
 */
class RetrofitFactory private constructor() {

    private val retrofit: Retrofit

    fun <T> create(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(initOkHttpClient())
            .build()

    }

    companion object {
        val instance by lazy {
            RetrofitFactory()
        }
    }

    private fun initOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(initCookieIntercept())
            .addInterceptor(initLoginIntercept())
            .addInterceptor(initCommonInterceptor())
            .build()
    }

    /**
     * cookie拦截器
     */
    private fun initCookieIntercept(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            val response = chain.proceed(request)
            val requestUrl = request.url().toString()
            val domain = request.url().host()
            //只保存登录或者注册
//            if(requestUrl.contains(Constant.SAVE_USER_LOGIN_KEY) || requestUrl.contains(Constant.SAVE_USER_REGISTER_KEY)){
//                val mCookie = response.headers(Constant.SET_COOKIE_KEY)
//                mCookie?.let {
//                    saveCookie(domain,parseCookie(it))
//                }
//            }
            response
        }

    }

    /**
     * 自动登陆逻辑
     */
    private fun initLoginIntercept(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            val builder = request.newBuilder()
            val domain = request.url().host()

//            if(domain.isNotEmpty()){
//                val mCookie by SPreference("cookie","")
//                if(mCookie.isNotEmpty()){
//                    builder.addHeader(Constant.COOKIE_NAME,mCookie)
//                }
//            }
            val response = chain.proceed(builder.build())
            response
        }
    }

    /**
     *请求头拦截
     */
    private fun initCommonInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("charset", "UTF-8")
                .build()

            chain.proceed(request)
        }
    }

    private fun parseCookie(it: List<String>): String {
        if (it.isEmpty()) {
            return ""
        }

        val stringBuilder = StringBuilder()

        it.forEach { cookie ->
            stringBuilder.append(cookie).append(";")
        }

        if (stringBuilder.isEmpty()) {
            return ""
        }
        //末尾的";"去掉
        return stringBuilder.deleteCharAt(stringBuilder.length - 1).toString()
    }

    private fun saveCookie(domain: String?, parseCookie: String) {
//        domain?.let {
//            var resutl :String by SPreference("cookie",parseCookie)
//            resutl = parseCookie
//        }
    }
}