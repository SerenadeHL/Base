package me.serenadehl.base.extensions

import android.app.Activity
import android.content.Context
import android.os.Build
import android.support.annotation.StringRes
import android.widget.Toast
import android.support.v4.app.Fragment
import me.serenadehl.base.utils.app.SystemUtils

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-02-14 22:47:26
 */

/**
 * Toast
 */
inline fun Context.toast(msg: String) = Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()

inline fun Fragment.toast(msg: String) = Toast.makeText(activity?.applicationContext, msg, Toast.LENGTH_SHORT).show()

inline fun Context.toast(@StringRes msgId: Int) = Toast.makeText(applicationContext, msgId, Toast.LENGTH_SHORT).show()

inline fun Fragment.toast(@StringRes msgId: Int) = Toast.makeText(activity?.applicationContext, msgId, Toast.LENGTH_SHORT).show()

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
