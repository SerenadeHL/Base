package me.serenadehl.base.extensions

import me.serenadehl.base.utils.gson.GsonExtensionUtils
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-11 16:34:30
 */

inline fun <reified T> String.fromJson(clazz: Class<T>): T? {
    if (!isJson()) return null
    return GsonExtensionUtils.getGson().fromJson(this, clazz)
}

inline fun <reified T> String.fromJsonArray(clazz: Class<T>): List<T>? {
    if (!isJson()) return null
    val type = type(List::class.java, clazz)
    return GsonExtensionUtils.getGson().fromJson(this, type)
}

fun type(raw: Class<*>, vararg args: Type): ParameterizedType {
    return object : ParameterizedType {
        override fun getRawType() = raw

        override fun getOwnerType() = null

        override fun getActualTypeArguments() = args
    }
}

inline fun Any.toJson():String{
    return  GsonExtensionUtils.getGson().toJson(this)
}

inline fun String.isJson(): Boolean {
    return (startsWith("{") && endsWith("}")) or (startsWith("[") && endsWith("]"))
}