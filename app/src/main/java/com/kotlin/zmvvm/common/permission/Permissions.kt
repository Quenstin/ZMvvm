package com.kotlin.zmvvm.common.permission

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData

/**
 * Created by zhgq on 2020/7/6
 * Describe：权限类
 */
class Permissions {
    @Volatile
    private var mPermissionFragment: PermissionFragment? = null

    private fun getInstance(fragmentManager: FragmentManager) =
        mPermissionFragment ?: synchronized(this) {
        mPermissionFragment?:if (fragmentManager.findFragmentByTag(TAG)==null) PermissionFragment().run {

            fragmentManager.beginTransaction().add(this, TAG).commitNow()
            this
        }else fragmentManager.findFragmentByTag(TAG) as PermissionFragment
        }

    companion object{
        const val TAG="permissions"
    }


    constructor(activity: AppCompatActivity){
        mPermissionFragment=getInstance(activity.supportFragmentManager)
    }

    constructor(fragment: Fragment) {
        mPermissionFragment = getInstance(fragment.childFragmentManager)
    }

    fun request(vararg permissions: String): MutableLiveData<PermissionResult> {
        return this.requestArray(permissions)
    }

    fun requestArray(permissions: Array<out String>): MutableLiveData<PermissionResult> {
        mPermissionFragment!!.requestPermissions(permissions)
        return mPermissionFragment!!.liveData
    }
}