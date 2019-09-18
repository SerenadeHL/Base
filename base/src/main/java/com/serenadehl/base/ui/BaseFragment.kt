package com.serenadehl.base.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.serenadehl.layoutinstaller.LayoutInstaller

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-09-11 23:59:37
 */
abstract class BaseFragment : Fragment() {
    protected var mContentView: View? = null

    private var isViewCreated = false // 界面是否已创建完成
    private var isVisibleToUser = false // 是否对用户可见
    private var isDataLoaded = false // 数据是否已请求

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mContentView = LayoutInstaller.bind(this, container)
        onViewCreated(savedInstanceState)
        return mContentView
    }

    abstract fun onViewCreated(savedInstanceState: Bundle?)

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        internalLazyLoadData()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isViewCreated = true
        internalLazyLoadData()
    }

    /**
     * 懒加载实现
     */
    protected open fun lazyLoadData() {

    }

    /**
     * 内部懒加载方法
     */
    private fun internalLazyLoadData() {
        if (isViewCreated && isVisibleToUser && !isDataLoaded) {
            lazyLoadData()
            isDataLoaded = true
        }
    }
}