package me.serenadehl.base.extensions

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.support.annotation.IntegerRes
import android.support.annotation.StringRes
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import me.serenadehl.base.BuildConfig
import me.serenadehl.base.utils.sharedpre.SPUtil
import android.support.v4.app.Fragment
import me.serenadehl.base.BaseApplication

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
inline fun Context.getStatusBarHeight(): Int {
    var result = 0
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = resources.getDimensionPixelSize(resourceId)
    }
    return result
}

/**
 * 设置虚拟按键颜色
 */
inline fun Activity.setNavigationBarColor(color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.navigationBarColor = color
    }
}

inline fun Fragment.setNavigationBarColor(color: Int) = activity?.setNavigationBarColor(color)
