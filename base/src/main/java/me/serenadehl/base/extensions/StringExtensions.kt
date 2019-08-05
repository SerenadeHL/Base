package me.serenadehl.base.extensions

import android.os.Build
import android.text.Html
import android.text.Spanned

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-26 16:01:51
 */

/**
 * String转Html
 */
inline fun String.toHtml(): CharSequence {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(this)
    }
}