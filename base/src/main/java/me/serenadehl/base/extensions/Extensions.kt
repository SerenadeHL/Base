package me.serenadehl.base.extensions

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import me.serenadehl.base.BuildConfig
import me.serenadehl.base.utils.sharedpre.SPUtil
import android.support.v4.app.Fragment

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

/**
 * 隐藏输入法
 *
 * @param view
 */
inline fun Context.hideKeyboard(view: View) {
    (applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(view.windowToken, 0)
}

/**
 * 显示输入法
 *
 * @param view
 */
inline fun Context.showKeyboard(view: View) {
    (applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(view, 0)
}

inline fun Fragment.hideKeyboard(view: View) = activity?.hideKeyboard(view)

inline fun Fragment.showKeyboard(view: View) = activity?.showKeyboard(view)


/**
 * 设置虚拟按键颜色
 */
inline fun Activity.setNavigationBarColor(color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.navigationBarColor = color
    }
}

/**
 * log
 */
inline fun Any.log() {
    if (BuildConfig.DEBUG) Log.e(TAG, toString())
}

/**
 * 保存到SP
 */
inline fun <T> T.saveToSP(key: String) = apply { SPUtil.putString(key, toString()) }