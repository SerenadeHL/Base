package com.serenadehl.base.ui.mvp

import java.lang.ref.WeakReference

abstract class MVPBasePresenter<V : IBaseView, M : IBaseModel> : IBasePresenter {
    protected lateinit var mView: WeakReference<V>
    protected var mModel: M

    init {
        mModel = this.createModel()
    }

    @Suppress("UNCHECKED_CAST")
    override fun attach(view: IBaseView) {
        this.mView = WeakReference(view as V)
    }

    override fun detach() {
        mView.clear()
    }

    abstract fun createModel(): M
}