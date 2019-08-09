package me.serenadehl.base.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-14 11:13:49
 */

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