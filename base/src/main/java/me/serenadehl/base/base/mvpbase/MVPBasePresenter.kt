package me.serenadehl.base.base.mvpbase

import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class MVPBasePresenter<V : IBaseView, M : IBaseModel> : IBasePresenter {
    val mCompositeDisposable by lazy { CompositeDisposable() }
    lateinit var mView: WeakReference<V>
    var mModel: M

    init {
        mModel = this.createModel()
    }

    @Suppress("UNCHECKED_CAST")
    override fun attach(view: IBaseView) {
        this.mView = WeakReference(view as V)
    }

    override fun detach() {
        mView.clear()
        mCompositeDisposable.dispose()
    }

    abstract fun createModel(): M
}