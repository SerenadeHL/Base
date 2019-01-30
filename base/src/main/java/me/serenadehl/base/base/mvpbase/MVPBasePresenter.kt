package me.serenadehl.base.base.mvpbase

import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class MVPBasePresenter : IBasePresenter {
    val mCompositeDisposable by lazy { CompositeDisposable() }
    lateinit var mView: WeakReference<IBaseView>
    var mModel: IBaseModel

    init {
        mModel = this.createModel()
    }

    override fun attach(view: IBaseView) {
        this.mView = WeakReference(view)
    }

    override fun detach() {
        mView.clear()
        mCompositeDisposable.dispose()
    }

    abstract fun createModel(): IBaseModel
}