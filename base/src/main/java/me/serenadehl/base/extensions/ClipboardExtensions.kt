package me.serenadehl.base.extensions

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.support.v4.app.Fragment

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
 * 复制到粘贴板
 */
inline fun Context.copyToClipboard(text: String) = text.copyToClipboard(this)

/**
 * 复制到粘贴板
 */
inline fun Fragment.copyToClipboard(text: String) = activity?.copyToClipboard(text)
