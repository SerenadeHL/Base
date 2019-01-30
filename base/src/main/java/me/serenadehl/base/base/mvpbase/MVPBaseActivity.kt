package me.serenadehl.base.base.mvpbase

import android.os.Bundle
import me.serenadehl.base.base.BaseActivity

abstract class MVPBaseActivity : BaseActivity(), IBaseView {
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