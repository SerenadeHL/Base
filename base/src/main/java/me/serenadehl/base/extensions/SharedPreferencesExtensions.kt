package me.serenadehl.base.extensions

import me.serenadehl.base.utils.sharedpre.SPUtil

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-03-21 18:48:09
 */

/**
 * 保存到SP
 */
inline fun <T> T.saveToSP(key: String) = apply { SPUtil.putString(key, toString()) }