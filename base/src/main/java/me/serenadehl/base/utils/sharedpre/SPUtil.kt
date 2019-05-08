package me.serenadehl.base.utils.sharedpre

import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson
import me.serenadehl.base.utils.app.AppManager

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-02-15 00:50:35
 */
object SPUtil {
    private val SP_NAME by lazy {
        AppManager.currentActivity
                .applicationContext
                .applicationInfo
                .packageName
    }
    private val mSharedPreferences by lazy {
        AppManager.currentActivity
                .applicationContext
                .getSharedPreferences(SP_NAME, MODE_PRIVATE)
    }

    fun putBoolean(key: String, value: Boolean) {
        mSharedPreferences
                .edit()
                .putBoolean(key, value)
                .apply()
    }

    fun getBoolean(key: String, defValue: Boolean = false) = mSharedPreferences.getBoolean(key, defValue)

    fun putString(key: String, value: String) {
        mSharedPreferences
                .edit()
                .putString(key, value)
                .apply()
    }

    fun getString(key: String, defValue: String = "") = mSharedPreferences.getString(key, defValue)

    fun putInt(key: String, value: Int) {
        mSharedPreferences
                .edit()
                .putInt(key, value)
                .apply()
    }

    fun getInt(key: String, defValue: Int = 0) = mSharedPreferences.getInt(key, defValue)

    fun putFloat(key: String, value: Float) {
        mSharedPreferences
                .edit()
                .putFloat(key, value)
                .apply()
    }

    fun getFloat(key: String, defValue: Float = 0F) = mSharedPreferences.getFloat(key, defValue)

    fun putLong(key: String, value: Long) {
        mSharedPreferences
                .edit()
                .putLong(key, value)
                .apply()
    }

    fun getLong(key: String, defValue: Long = 0L) = mSharedPreferences.getLong(key, defValue)

    fun putArrayList(key: String, value: List<out Any>) = putString(key, Gson().toJson(value))

    fun <T> getArrayList(key: String) = Gson().fromJson(mSharedPreferences.getString(key, "[]"), ArrayList::class.java) as ArrayList<T>
}