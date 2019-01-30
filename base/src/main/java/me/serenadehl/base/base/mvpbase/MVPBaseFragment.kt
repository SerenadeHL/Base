package me.serenadehl.base.base.mvpbase

import android.os.Bundle
import me.serenadehl.base.base.BaseFragment

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-11-05 15:52:29
 */
abstract class MVPBaseFragment : BaseFragment(), IBaseView {
    val mPresenter: IBasePresenter

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

    abstract fun createPresenter(): IBasePresenter
}