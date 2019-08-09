package me.serenadehl.base.utils.locale

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import me.serenadehl.base.extensions.log
import me.serenadehl.base.utils.sharedpre.SPUtils
import java.util.*

/**
 * 多语言切换
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-06-03 10:32:33
 */
object LocaleUtils {
    private const val LOCALE = "locale"

    const val AUTO = 0//跟随系统语言
    const val CN = 1//中文
    const val EN = 2//英文

    private val mDefaultLocale = Locale.ENGLISH

    private val mSupportList = arrayOf("EN", "CN")

    /**
     * 获取系统设置Locale
     */
    private fun getSystemLocale(): Locale {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList.getDefault().get(0)
        } else {
            Locale.getDefault()
        }
    }

    /**
     * 获取自己设置的Locale，不支持的语言会默认显示英文
     */
    fun getPreferLocale(context: Context): Locale {
        return when (getLanguage(context)) {
            AUTO -> with(getSystemLocale()) {
                return if (this@with.country in mSupportList) this@with else mDefaultLocale
            }
            CN -> Locale.SIMPLIFIED_CHINESE
            EN -> Locale.ENGLISH
            else -> {
                "Can't Support This Language,Show Default".log()
                mDefaultLocale
            }
        }
    }

    /**
     * 设置语言
     * @param locale AUTO,CN,EN
     */
    fun setLanguage(context: Context, locale: Int) {
        SPUtils.putInt(context, LOCALE, locale)
        setApplicationLanguage(context)
    }

    /**
     * 获取语言
     */
    fun getLanguage(context: Context) = SPUtils.getInt(context, LOCALE, AUTO)

    /**
     * 获取修改资源后的Context
     */
    fun getModifiedContext(context: Context): Context {
        val locale = getPreferLocale(context)
        val res = context.resources
        val config = Configuration(res.configuration)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale)
            context.createConfigurationContext(config)
        } else {
            config.locale = locale
            res.updateConfiguration(config, res.displayMetrics)
            context
        }
    }

    /**
     * 切换语言
     */
    fun setApplicationLanguage(context: Context) {
        val resources = context.applicationContext.resources
        val dm = resources.displayMetrics
        val config = resources.configuration
        val locale = getPreferLocale(context)
        config.locale = locale
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val localeList = LocaleList(locale)
            LocaleList.setDefault(localeList)
            config.locales = localeList
            context.applicationContext.createConfigurationContext(config)
            Locale.setDefault(locale)
        }
        resources.updateConfiguration(config, dm)
    }
}