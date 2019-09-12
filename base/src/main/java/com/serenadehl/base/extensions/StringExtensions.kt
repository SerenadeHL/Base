package com.serenadehl.base.extensions

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.text.Html

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-14 11:11:39
 */

/**
 * 复制到粘贴板
 */
inline fun String.copyToClipboard(context: Context) {
    val manager = context.applicationContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("text", this)
    manager.primaryClip = clip
}


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

/**
 * 删除Html <div>和<p>尾部的空行
 */
inline fun CharSequence.deleteEndBlankLine(): CharSequence {
    var index = length - 1
    while (index >= 0 && Character.isWhitespace(this[index])) {
        index--
    }
    return if (index < 0) "" else subSequence(0, index + 1)
}