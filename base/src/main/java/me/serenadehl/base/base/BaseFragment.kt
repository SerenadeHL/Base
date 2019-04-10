package me.serenadehl.base.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-02-27 09:05:22
 */
abstract class BaseFragment : Fragment() {
    protected lateinit var mRootView: View

    private var isViewCreated = false // 界面是否已创建完成
    private var isVisibleToUser = false // 是否对用户可见
    private var isDataLoaded = false // 数据是否已请求

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater.inflate(layout(), null)
        onViewCreated(savedInstanceState)
        return mRootView
    }

    /**
     * 设置布局
     * @return 布局id
     */
    abstract fun layout(): Int

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