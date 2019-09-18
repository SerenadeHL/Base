package com.serenadehl.base.ui

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import com.serenadehl.base.utils.AppManager
import com.serenadehl.layoutinstaller.LayoutInstaller
import com.serenadehl.localemanager.LocaleManager

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-09-11 01:17:08
 */
abstract class BaseActivity : AppCompatActivity() {
    protected var mContentView: View? = null

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleManager.getContext(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //取消ActionBar
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        mContentView = LayoutInstaller.bind(this)
        AppManager.addActivity(this)
        onActivityCreated(savedInstanceState)
    }

    override fun onDestroy() {
        AppManager.removeActivity(this)
        super.onDestroy()
    }

    abstract fun onActivityCreated(savedInstanceState: Bundle?)
}