package com.kotlin.zmvvm.common.permission

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData

/**
 * Created by zhgq on 2020/7/6
 * Describe：
 */
class PermissionFragment : Fragment() {
    lateinit var liveData: MutableLiveData<PermissionResult>

    private val PERMISSION_REQUEST_CODE=10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance=true
    }
    fun requestPermissions(permissions: Array<out String>) {
        liveData = MutableLiveData()
        requestPermissions(permissions, PERMISSION_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (requestCode==PERMISSION_REQUEST_CODE){
            val dem=ArrayList<String>()
            val rat=ArrayList<String>()
            for ((index,value) in grantResults.withIndex()){

                if (value==PackageManager.PERMISSION_DENIED){
                    if (shouldShowRequestPermissionRationale(permissions[index])) {
                        rat.add(permissions[index])
                    } else {
                        dem.add(permissions[index])
                    }
                }
                if (dem.isEmpty() && rat.isEmpty()) {
                    liveData.value = PermissionResult.Grant
                } else {
                    if (rat.isNotEmpty()) {
                        liveData.value = PermissionResult.Rationale(rat.toTypedArray())
                    } else if (dem.isNotEmpty()) {
                        liveData.value = PermissionResult.Deny(dem.toTypedArray())
                    }
                }
            }
        }
    }

}