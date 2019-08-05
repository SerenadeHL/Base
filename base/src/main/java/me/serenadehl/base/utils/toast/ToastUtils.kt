package me.serenadehl.base.utils.toast

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Message
import android.support.annotation.StringRes
import android.widget.Toast

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-08-05 14:29:51
 */
object ToastUtils {
    lateinit var mToast: Toast
    private val sTN by lazy {
        val field = Toast::class.java.getDeclaredField("mTN")
        field.isAccessible = true
        return@lazy field
    }
    private val sTNHandler by lazy {
        val field = sTN.type.getDeclaredField("mHandler")
        field.isAccessible = true
        return@lazy field
    }

    private fun hook(toast: Toast) {
        try {
            val tn = sTN.get(toast)
            val preHandler = sTNHandler.get(tn) as Handler
            sTNHandler.set(tn, SafelyHandlerWrapper(preHandler))
        } catch (e: Exception) {
        }
    }

    @SuppressLint("ShowToast")
    fun toast(context: Context?, charSequence: CharSequence, duration: Int) {
        if (!::mToast.isInitialized) {
            mToast = Toast.makeText(context, charSequence, duration)
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) {
                hook(mToast)
            }
        } else {
            mToast.setText(charSequence)
            mToast.duration = duration
        }
        mToast.show()
    }

    @SuppressLint("ShowToast")
    fun toast(context: Context?, @StringRes resId: Int, duration: Int) {
        if (!::mToast.isInitialized) {
            mToast = Toast.makeText(context, resId, duration)
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N_MR1) {
                hook(mToast)
            }
        } else {
            mToast.setText(resId)
            mToast.duration = duration
        }
        mToast.show()
    }


    internal class SafelyHandlerWrapper(private val impl: Handler) : Handler() {
        override fun dispatchMessage(msg: Message?) {
            try {
                super.dispatchMessage(msg)
            } catch (e: Exception) {
            }
        }

        override fun handleMessage(msg: Message?) {
            impl.handleMessage(msg)//需要委托给原Handler执行
        }
    }
}