package com.kotlin.zmvvm.base.observer

import androidx.lifecycle.MutableLiveData
import com.kotlin.zmvvm.base.repository.BaseRepository
import com.kotlin.zmvvm.common.state.State
import com.kotlin.zmvvm.common.state.StateType
import com.kotlin.zmvvm.common.utils.Constant
import com.kotlin.zmvvm.network.response.BaseResponse
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by zhgq on 2020/6/15
 * Describe：
 */
class BaseObserver<T : BaseResponse<*>>(
    val liveData: MutableLiveData<T>,
    val loadState: MutableLiveData<State>,
    val baseRepository: BaseRepository
) : Observer<T> {
    override fun onNext(response: T) {
        when (response.errorCode) {
            Constant.SUCCESS -> {
                if (response.data is List<*>) {
                    if ((response.data as List<*>).isEmpty()) {
                        loadState.postValue(State(StateType.EMPTY))
                        return
                    }
                }

                loadState.postValue(State(StateType.SUCCESS))
                liveData.postValue(response)
            }
            Constant.NOT_LOGIN->{
//                UserInfo.instance.logoutSuccess()
                loadState.postValue(State(StateType.ERROR,message ="请重新登录"))

            }
            else ->{
                loadState.postValue(State(StateType.ERROR, message = response.errorManager))

            }

        }
    }

    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
        baseRepository.addSubscribe(d)
    }

    override fun onError(e: Throwable) {
        loadState.postValue(State(StateType.NETWORK_ERROR))

    }


}