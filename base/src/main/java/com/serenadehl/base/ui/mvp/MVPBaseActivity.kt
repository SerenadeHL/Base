package com.serenadehl.base.ui.mvp

import android.os.Bundle
import com.serenadehl.base.ui.BaseActivity

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-09-12 01:44:29
 */
abstract class MVPBaseActivity<P : IBasePresenter> : BaseActivity(), IBaseView {
    protected val mPresenter: P

    init {
        mPresenter = this.createPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        mPresenter.attach(this)
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        mPresenter.detach()
        super.onDestroy()
    }

    abstract fun createPresenter(): P
}