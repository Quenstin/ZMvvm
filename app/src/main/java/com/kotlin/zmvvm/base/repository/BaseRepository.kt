package com.kotlin.zmvvm.base.repository

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by zhgq on 2020/6/15
 * Describe：rep基类
 */
open class BaseRepository {
    private val mCompositeDisposable by  lazy{ CompositeDisposable()}

    fun addSubscribe(disposable: Disposable)=mCompositeDisposable.add(disposable)

    fun unSubscribe()=mCompositeDisposable.dispose()
}