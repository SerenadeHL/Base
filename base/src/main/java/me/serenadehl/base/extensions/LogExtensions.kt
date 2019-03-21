package me.serenadehl.base.extensions

import android.util.Log
import me.serenadehl.base.BaseApplication

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-11-05 15:41:44
 */
const val TAG = "=========="

/**
 * log
 */
inline fun Any.log() {
    if (BaseApplication.DEBUG) Log.e(TAG, toString())
}