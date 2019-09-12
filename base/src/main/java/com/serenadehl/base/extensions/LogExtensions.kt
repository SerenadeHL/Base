package com.serenadehl.base.extensions

import android.util.Log
import com.serenadehl.base.BaseApplication

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-11-05 15:41:44
 */
const val TAG = "==========>"

/**
 * log
 */
inline fun Any.log(any: Any) {
    if (BaseApplication.DEBUG) Log.e(" ${any.javaClass.simpleName} $TAG ", toString())
}

inline fun Any.log(tag: String) {
    if (BaseApplication.DEBUG) Log.e(" $tag $TAG ", toString())
}