package com.serenadehl.base.extensions

import android.app.Activity
import android.content.Context
import android.os.Build
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.widget.Toast
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.WindowManager
import com.serenadehl.base.utils.SystemUtils
import com.serenadehl.base.utils.ToastUtils
import java.util.function.BinaryOperator

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-02-14 22:47:26
 */

/**
 * Toast
 */
inline fun Context.toast(msg: CharSequence?) = ToastUtils.toast(applicationContext, msg, Toast.LENGTH_SHORT)

inline fun Fragment.toast(msg: CharSequence?) = requireActivity().toast(msg)

inline fun Context.toast(@StringRes msgId: Int) = ToastUtils.toast(applicationContext, msgId, Toast.LENGTH_SHORT)

inline fun Fragment.toast(@StringRes msgId: Int) = requireActivity().toast(msgId)

inline fun Context.getColorResource(@ColorRes id: Int) = ContextCompat.getColor(this, id)

inline fun Fragment.getColorResource(@ColorRes id: Int) = requireActivity().getColorResource(id)

/**
 * 页面全屏
 */
inline fun Activity.fullscreen(enable: Boolean, hideStatusBar: Boolean) {
    val flag = if (hideStatusBar) {
        WindowManager.LayoutParams.FLAG_FULLSCREEN
    } else {
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
    }
    if (enable) {
        window.addFlags(flag)
    } else {
        window.clearFlags(flag)
    }
}

inline fun Fragment.fullscreen(enable: Boolean, hideStatusBar: Boolean) =
    requireActivity().fullscreen(enable, hideStatusBar)

/**
 * 设置状态栏字体颜色
 */
inline fun Activity.setStatusBarFontColor(darkFont: Boolean) {
    if (darkFont) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    } else {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }
}

inline fun Fragment.setStatusBarFontColor(darkFont: Boolean) = requireActivity().setStatusBarFontColor(darkFont)

/**
 * 获取状态栏高度
 */
inline fun Context.getStatusBarHeight() = SystemUtils.getStatusBarHeight(this)

/**
 * 获取虚拟按键高度
 */
inline fun Context.getNavigationBarHeight() = SystemUtils.getNavigationBarHeight(this)

/**
 * 设置虚拟按键颜色
 */
inline fun Activity.setNavigationBarColor(color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.navigationBarColor = color
    }
}

inline fun Fragment.setNavigationBarColor(color: Int) = requireActivity().setNavigationBarColor(color)
