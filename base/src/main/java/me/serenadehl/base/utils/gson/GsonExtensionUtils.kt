package me.serenadehl.base.utils.gson

import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-07 21:04:33
 */
object GsonExtensionUtils {
    lateinit var mGson: Gson

    fun setGson(gson: Gson) {
        mGson = gson
    }

    fun getGson(): Gson {
        if (!::mGson.isInitialized) mGson = Gson()
        return mGson
    }
}