package me.serenadehl.base

import android.app.Application

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
        DEBUG = isDebug()
    }

    abstract fun isDebug(): Boolean
}