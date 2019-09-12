package com.serenadehl.base

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.res.Configuration
import com.serenadehl.localemanager.LocaleManager
import java.util.*

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-02-21 16:55:25
 */

abstract class BaseApplication : Application() {

    companion object {
        var DEBUG: Boolean = false
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocaleManager.getContext(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        LocaleManager.inject(this)
    }

    override fun onCreate() {
        super.onCreate()
        DEBUG = applicationInfo != null && applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
        LocaleManager.setDefaultLocale(Locale.SIMPLIFIED_CHINESE)
        LocaleManager.inject(this)
    }
}