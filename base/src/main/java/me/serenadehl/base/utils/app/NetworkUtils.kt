package me.serenadehl.base.utils.app

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.telephony.TelephonyManager


/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-16 14:46:21
 */
object NetworkUtils {
    const val NETWORK_NONE = 0 //没有网络连接
    const val NETWORK_WIFI = 1 //wifi连接
    const val NETWORK_2G = 2 //2G
    const val NETWORK_3G = 3 //3G
    const val NETWORK_4G = 4 //4G
    const val NETWORK_UNKNOWN = 5 //未知

    /**
     * 获取当前网络连接的类型
     * 需要ACCESS_NETWORK_STATE权限
     */
    @SuppressLint("MissingPermission")
    fun getNetworkType(context: Context): Int {
        val connManager = context.applicationContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager? ?: return NETWORK_NONE
        try {
            // 获取网络类型，如果为空，返回无网络
            val activeNetInfo = connManager.activeNetworkInfo
            if (activeNetInfo == null || !activeNetInfo.isAvailable) {
                return NETWORK_NONE
            }
            // 判断是否为WIFI
            val wifiInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            if (null != wifiInfo) {
                val state = wifiInfo.state
                if (null != state) {
                    if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
                        return NETWORK_WIFI
                    }
                }
            }
            // 若不是WIFI，则去判断是2G、3G、4G网
            val telephonyManager = context.applicationContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            when (telephonyManager.networkType) {
                /*
             GPRS : 2G(2.5) General Packet Radia Service 114kbps
             EDGE : 2G(2.75G) Enhanced Data Rate for GSM Evolution 384kbps
             UMTS : 3G WCDMA 联通3G Universal Mobile Telecommunication System 完整的3G移动通信技术标准
             CDMA : 2G 电信 Code Division Multiple Access 码分多址
             EVDO_0 : 3G (EVDO 全程 CDMA2000 1xEV-DO) Evolution - Data Only (Data Optimized) 153.6kps - 2.4mbps 属于3G
             EVDO_A : 3G 1.8mbps - 3.1mbps 属于3G过渡，3.5G
             1xRTT : 2G CDMA2000 1xRTT (RTT - 无线电传输技术) 144kbps 2G的过渡,
             HSDPA : 3.5G 高速下行分组接入 3.5G WCDMA High Speed Downlink Packet Access 14.4mbps
             HSUPA : 3.5G High Speed Uplink Packet Access 高速上行链路分组接入 1.4 - 5.8 mbps
             HSPA : 3G (分HSDPA,HSUPA) High Speed Packet Access
             IDEN : 2G Integrated Dispatch Enhanced Networks 集成数字增强型网络 （属于2G，来自维基百科）
             EVDO_B : 3G EV-DO Rev.B 14.7Mbps 下行 3.5G
             LTE : 4G Long Term Evolution FDD-LTE 和 TDD-LTE , 3G过渡，升级版 LTE Advanced 才是4G
             EHRPD : 3G CDMA2000向LTE 4G的中间产物 Evolved High Rate Packet Data HRPD的升级
             HSPAP : 3G HSPAP 比 HSDPA 快些
             */
                // 2G网络
                TelephonyManager.NETWORK_TYPE_GPRS, TelephonyManager.NETWORK_TYPE_CDMA, TelephonyManager.NETWORK_TYPE_EDGE, TelephonyManager.NETWORK_TYPE_1xRTT, TelephonyManager.NETWORK_TYPE_IDEN -> return NETWORK_2G
                // 3G网络
                TelephonyManager.NETWORK_TYPE_EVDO_A, TelephonyManager.NETWORK_TYPE_UMTS, TelephonyManager.NETWORK_TYPE_EVDO_0, TelephonyManager.NETWORK_TYPE_HSDPA, TelephonyManager.NETWORK_TYPE_HSUPA, TelephonyManager.NETWORK_TYPE_HSPA, TelephonyManager.NETWORK_TYPE_EVDO_B, TelephonyManager.NETWORK_TYPE_EHRPD, TelephonyManager.NETWORK_TYPE_HSPAP -> return NETWORK_3G
                // 4G网络
                TelephonyManager.NETWORK_TYPE_LTE -> return NETWORK_4G
                else -> return NETWORK_UNKNOWN
            }
        }catch (e:Exception){
            return NETWORK_UNKNOWN
        }
    }

    /**
     * 判断网络是否连接
     * 需要ACCESS_NETWORK_STATE权限
     */
    @SuppressLint("MissingPermission")
    fun isNetworkConnected(context: Context): Boolean {
        val connectivity = context.applicationContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        try {
            val info = connectivity.activeNetworkInfo
            if (info != null && info.isConnected) {
                if (info.state == NetworkInfo.State.CONNECTED) {
                    return true
                }
            }
            return false
        } catch (e: Exception) {
            return false
        }

    }

    /**
     * 判断是否wifi连接
     * 需要ACCESS_NETWORK_STATE权限
     */
    @SuppressLint("MissingPermission")
    fun isWifiConnected(context: Context): Boolean {
        val connectivityManager = context.applicationContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        try {
            val networkInfo = connectivityManager.activeNetworkInfo
            if (networkInfo != null) {
                val networkInfoType = networkInfo.type
                if (networkInfoType == ConnectivityManager.TYPE_WIFI || networkInfoType == ConnectivityManager.TYPE_ETHERNET) {
                    return networkInfo.isConnected
                }
            }
            return false
        } catch (e: Exception) {
            return false
        }
    }
}