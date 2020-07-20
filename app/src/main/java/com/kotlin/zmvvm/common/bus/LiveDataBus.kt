package com.kotlin.zmvvm.common.bus

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.lang.reflect.Field
import java.lang.reflect.Method
import androidx.lifecycle.LifecycleOwner as LifecycleOwner1


/**
 * Created by zhgq on 2020/7/15
 * Describe：使用livedata实现消息总线
 */
class LiveDataBus private constructor() {
//    private val bus: MutableMap<String?, BusMutableLiveData<Any?>?>?
//
//    private object SingletonHolder {
//        val DEFAULT_BUS: LiveDataBus? = LiveDataBus()
//    }
//
//    fun <T> with(key: String?, type: Class<T?>?): MutableLiveData<T?>? {
//        if (!bus!!.containsKey(key)) {
//            bus[key] = BusMutableLiveData()
//        }
//        return bus[key] as MutableLiveData<T?>?
//    }
//
//    fun with(key: String?): MutableLiveData<Any?>? {
//        return with<Any?>(key, Any::class.java)
//    }
//
//    private class ObserverWrapper<T>(observer: Observer<T?>?) : Observer<T?> {
//        private val observer: Observer<T?>?
//        fun onChanged(@Nullable t: T?) {
//            if (observer != null) {
//                if (isCallOnObserve) {
//                    return
//                }
//                observer.onChanged(t)
//            }
//        }
//
//        private val isCallOnObserve: Boolean
//            private get() {
//                val stackTrace =
//                    Thread.currentThread().stackTrace
//                if (stackTrace != null && stackTrace.size > 0) {
//                    for (element in stackTrace) {
//                        if ("android.arch.lifecycle.LiveData" == element!!.className && "observeForever" == element.methodName) {
//                            return true
//                        }
//                    }
//                }
//                return false
//            }
//
//        init {
//            this.observer = observer
//        }
//    }
//
//    private class BusMutableLiveData<T> : MutableLiveData<T?>() {
//        private val observerMap = HashMap<Any, Any>()
//        fun observe(
//
//            @NonNull owner: LifecycleOwner1?,
//            @NonNull observer: Observer<T?>?
//        ) {
//            super.observe(owner!!, observer)
//            try {
//                hook(observer)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//
//        fun observeForever(@NonNull observer: Observer<T?>?) {
//            if (!observerMap!!.containsKey(observer)) {
//                observerMap[observer] = ObserverWrapper<Any?>(observer)
//            }
//            super.observeForever(observerMap[observer]!!)
//        }
//
//        fun removeObserver(@NonNull observer: Observer<T?>?) {
//            var realObserver: Observer? = null
//            realObserver = if (observerMap!!.containsKey(observer)) {
//                observerMap.remove(observer)
//            } else {
//                observer
//            }
//            super.removeObserver(realObserver)
//        }
//
//        @Throws(Exception::class)
//        private fun hook(@NonNull observer: Observer<T?>?) {
//            //get wrapper's version
//            val classLiveData: Class<LiveData<*>?> = LiveData::class.java
//            val fieldObservers: Field = classLiveData.getDeclaredField("mObservers")
//            fieldObservers.setAccessible(true)
//            val objectObservers: Any = fieldObservers.get(this)
//            val classObservers: Class<*> = objectObservers.javaClass
//            val methodGet: Method = classObservers.getDeclaredMethod("get", Any::class.java)
//            methodGet.setAccessible(true)
//            val objectWrapperEntry: Any = methodGet.invoke(objectObservers, observer)
//            var objectWrapper: Any? = null
//            if (objectWrapperEntry is MutableMap.MutableEntry<*, *>) {
//                objectWrapper =
//                    objectWrapperEntry.value
//            }
//            if (objectWrapper == null) {
//                throw NullPointerException("Wrapper can not be bull!")
//            }
//            val classObserverWrapper: Class<*>? = objectWrapper.javaClass.superclass
//            val fieldLastVersion: Field = classObserverWrapper!!.getDeclaredField("mLastVersion")
//            fieldLastVersion.setAccessible(true)
//            //get livedata's version
//            val fieldVersion: Field = classLiveData.getDeclaredField("mVersion")
//            fieldVersion.setAccessible(true)
//            val objectVersion: Any = fieldVersion.get(this)
//            //set wrapper's version
//            fieldLastVersion.set(objectWrapper, objectVersion)
//        }
//    }
//
//    companion object {
//        fun get(): LiveDataBus? {
//            return SingletonHolder.DEFAULT_BUS
//        }
//    }
//
//    init {
//        bus = HashMap()
//    }
}