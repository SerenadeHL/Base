package me.serenadehl.base.extensions

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-05-05 19:48:43
 */
/**
 * RxJava2线程切换
 */
inline fun <reified T> Observable<T?>.async(): Observable<T?> {
    return subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onTerminateDetach()
}

/**
 * RxJava2添加绑定
 */
inline fun <reified T> Observable<T?>.addDisposable(compositeDisposable: CompositeDisposable): Observable<T?> {
    return doOnSubscribe { compositeDisposable.add(it) }
}