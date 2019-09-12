package com.serenadehl.base.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-02-15 00:50:35
 */
object SPUtils {

    private fun getSharedPreferences(context: Context): SharedPreferences {
        val spName = context.applicationInfo.packageName
        return context.getSharedPreferences(spName, MODE_PRIVATE)
    }

    fun putBoolean(context: Context, key: String, value: Boolean) {
        getSharedPreferences(context)
            .edit()
            .putBoolean(key, value)
            .apply()
    }

    fun getBoolean(context: Context, key: String, defValue: Boolean = false): Boolean {
        return getSharedPreferences(context).getBoolean(key, defValue)
    }

    fun putString(context: Context, key: String, value: String) {
        getSharedPreferences(context)
            .edit()
            .putString(key, value)
            .apply()
    }

    fun getString(context: Context, key: String, defValue: String = ""): String {
        return getSharedPreferences(context).getString(key, defValue) ?: ""
    }

    fun putInt(context: Context, key: String, value: Int) {
        getSharedPreferences(context)
            .edit()
            .putInt(key, value)
            .apply()
    }

    fun getInt(context: Context, key: String, defValue: Int = 0): Int {
        return getSharedPreferences(context).getInt(key, defValue)
    }

    fun putFloat(context: Context, key: String, value: Float) {
        getSharedPreferences(context)
            .edit()
            .putFloat(key, value)
            .apply()
    }

    fun getFloat(context: Context, key: String, defValue: Float = 0F): Float {
        return getSharedPreferences(context).getFloat(key, defValue)
    }

    fun putLong(context: Context, key: String, value: Long) {
        getSharedPreferences(context)
            .edit()
            .putLong(key, value)
            .apply()
    }

    fun getLong(context: Context, key: String, defValue: Long = 0L): Long {
        return getSharedPreferences(context).getLong(key, defValue)
    }
}