package me.serenadehl.base.utils.sharedpre

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson
import me.serenadehl.base.utils.gson.GsonExtensionUtils

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

    fun getBoolean(context: Context, key: String, defValue: Boolean = false) = getSharedPreferences(context).getBoolean(key, defValue)

    fun putString(context: Context, key: String, value: String) {
        getSharedPreferences(context)
                .edit()
                .putString(key, value)
                .apply()
    }

    fun getString(context: Context, key: String, defValue: String = "") = getSharedPreferences(context).getString(key, defValue)

    fun putInt(context: Context, key: String, value: Int) {
        getSharedPreferences(context)
                .edit()
                .putInt(key, value)
                .apply()
    }

    fun getInt(context: Context, key: String, defValue: Int = 0) = getSharedPreferences(context).getInt(key, defValue)

    fun putFloat(context: Context, key: String, value: Float) {
        getSharedPreferences(context)
                .edit()
                .putFloat(key, value)
                .apply()
    }

    fun getFloat(context: Context, key: String, defValue: Float = 0F) = getSharedPreferences(context).getFloat(key, defValue)

    fun putLong(context: Context, key: String, value: Long) {
        getSharedPreferences(context)
                .edit()
                .putLong(key, value)
                .apply()
    }

    fun getLong(context: Context, key: String, defValue: Long = 0L) = getSharedPreferences(context).getLong(key, defValue)

    fun putArrayList(context: Context, key: String, value: List<out Any>) = putString(context, key, Gson().toJson(value))

    fun <T> getArrayList(context: Context, key: String) = GsonExtensionUtils.getGson().fromJson(getSharedPreferences(context).getString(key, "[]"), ArrayList::class.java) as ArrayList<T>
}