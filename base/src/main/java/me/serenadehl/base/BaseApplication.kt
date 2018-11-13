package me.serenadehl.base

import android.app.Application
import me.serenadehl.base.utils.app.ActivityLifecycleCallback

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
        registerActivityLifecycleCallbacks(ActivityLifecycleCallback())
        DEBUG = isDebug()
    }

    abstract fun isDebug(): Boolean
}