package com.dong.dapp.utils

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context
import android.content.Context.TELEPHONY_SERVICE
import android.content.Context.WINDOW_SERVICE
import android.os.Build
import android.support.v4.content.ContextCompat.getSystemService
import android.telephony.TelephonyManager
import android.util.DisplayMetrics
import android.view.WindowManager
import me.serenadehl.base.utils.app.AppManager
import java.net.NetworkInterface.getNetworkInterfaces
import java.util.*


/**
 * 获取系统参数
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-16 16:30:12
 */
object SystemUtils {

    /**
     * 获取设备屏幕宽度
     */
    fun getScreenWidth(): Int {
        val currentActivity = AppManager.instance.currentActivity
        val wm = currentActivity.getSystemService(WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        wm.defaultDisplay.getRealMetrics(dm)
        return dm.widthPixels
    }

    /**
     * 获取设备屏幕高度
     */
    fun getScreenHeight(): Int {
        val currentActivity = AppManager.instance.currentActivity
        val wm = currentActivity.getSystemService(WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        wm.defaultDisplay.getRealMetrics(dm)
        return dm.heightPixels
    }

    /**
     * 获取手机品牌
     */
    fun getDeviceBrand(): String {
        return Build.BRAND
    }

    /**
     * 获取手机型号
     */
    fun getDeviceModel(): String {
        return Build.MODEL
    }

    /**
     * 获取Android系统版本号
     */
    fun getSystemVersion(): String {
        return Build.VERSION.RELEASE
    }

    /**
     * 获取App版本名
     */
    fun getAppVersionName(): String {
        val currentActivity = AppManager.instance.currentActivity
        // 获取PackageManager的实例
        val packageManager = currentActivity.packageManager
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        val packInfo = packageManager.getPackageInfo(currentActivity.packageName, 0)
        return packInfo.versionName
    }

    /**
     * 获取App版本号
     */
    fun getAppVersionCode(): String {
        val currentActivity = AppManager.instance.currentActivity
        // 获取PackageManager的实例
        val packageManager = currentActivity.packageManager
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        val packInfo = packageManager.getPackageInfo(currentActivity.packageName, 0)
        return packInfo.versionCode.toString()
    }

    /**
     * 获取IMEI
     * 需要READ_PHONE_STATE权限
     */
    @SuppressLint("MissingPermission", "HardwareIds")
    fun getIMEI(): String {
        val currentActivity = AppManager.instance.currentActivity
        val telephonyManager = currentActivity.getSystemService(TELEPHONY_SERVICE) as TelephonyManager
        return try {
            telephonyManager.deviceId ?: ""
        } catch (e: Exception) {
            ""
        }
    }

    /**
     * 获取Mac地址
     */
    fun getMacAddress(): String {
        try {
            val networkInterfaces = Collections.list(getNetworkInterfaces())
            for (networkInterface in networkInterfaces) {
                if (!networkInterface.name.equals("wlan0", true)) continue

                val macBytes = networkInterface.hardwareAddress ?: return ""

                val res1 = StringBuilder()
                for (b in macBytes) {
                    res1.append(String.format("%02X:", b))
                }

                if (res1.isNotEmpty()) {
                    res1.deleteCharAt(res1.length - 1)
                }
                return res1.toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return "02:00:00:00:00:00"
    }

    /**
     * 获取当前进程名
     */
    fun getCurrentProcessName(context: Context): String {
        val pid = android.os.Process.myPid()
        val manager = context.applicationContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (process in manager.runningAppProcesses) {
            if (process.pid == pid) {
                return process.processName
            }
        }
        return ""
    }

    /**
     * 是否是主进程
     */
    private fun isMainProcess(context: Context): Boolean {
        return context.applicationContext.packageName == getCurrentProcessName(context)
    }
}