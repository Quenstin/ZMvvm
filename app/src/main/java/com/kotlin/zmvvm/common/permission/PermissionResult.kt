package com.kotlin.zmvvm.common.permission

/**
 * Created by zhgq on 2020/7/6
 * Describe：
 */
 sealed class PermissionResult {

    object Grant : PermissionResult()
    class Deny(val permisssions : Array<String>):PermissionResult()
    class Rationale(val permisssions: Array<String>):PermissionResult()
}