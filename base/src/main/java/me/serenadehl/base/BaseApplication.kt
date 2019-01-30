package me.serenadehl.base

import android.app.Application
import android.content.pm.ApplicationInfo

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-02-21 16:55:25
 */

abstract class BaseApplication : Application() {
    companion object {
        var DEBUG: Boolean = false
    }

    override fun onCreate() {
        super.onCreate()
        DEBUG = applicationInfo != null && applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
    }
}