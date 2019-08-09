package me.serenadehl.base.extensions

import android.app.Activity
import android.content.Context
import android.os.Build
import android.support.annotation.StringRes
import android.widget.Toast
import androidx.core.app.Fragment
import me.serenadehl.base.utils.app.SystemUtils
import me.serenadehl.base.utils.toast.ToastUtils

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-02-14 22:47:26
 */

/**
 * Toast
 */
inline fun Context.toast(msg: String) = ToastUtils.toast(applicationContext, msg, Toast.LENGTH_SHORT)

inline fun Fragment.toast(msg: String) = ToastUtils.toast(activity?.applicationContext, msg, Toast.LENGTH_SHORT)

inline fun Context.toast(@StringRes msgId: Int) = ToastUtils.toast(applicationContext, msgId, Toast.LENGTH_SHORT)

inline fun Fragment.toast(@StringRes msgId: Int) = ToastUtils.toast(activity?.applicationContext, msgId, Toast.LENGTH_SHORT)

/**
 * 获取状态栏高度
 */
inline fun Context.getStatusBarHeight() = SystemUtils.getStatusBarHeight(this)

/**
 * 获取虚拟按键高度
 */
inline fun Context.getNavigationHeight() = SystemUtils.getNavigationHeight(this)

/**
 * 设置虚拟按键颜色
 */
inline fun Activity.setNavigationBarColor(color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.navigationBarColor = color
    }
}

inline fun Fragment.setNavigationBarColor(color: Int) = activity?.setNavigationBarColor(color)
