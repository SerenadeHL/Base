package com.serenadehl.base.extensions

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-05-05 19:49:23
 */

inline fun View.gone() = apply { visibility = View.GONE }

inline fun View.visible() = apply { visibility = View.VISIBLE }

inline fun View.invisible() = apply { visibility = View.INVISIBLE }

/**
 * 测量View并获取View宽度
 */
inline fun View.measureAndGetMeasuredWidth(): Int {
    measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
    return measuredWidth
}

/**
 * 测量View并获取View高度
 */
inline fun View.measureAndGetMeasuredHeight(): Int {
    measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
    return measuredHeight
}

/**
 * 获取View绘制图片，需要在View绘制结束后调用
 */
inline fun View.getBitmap(): Bitmap {
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    layout(0, 0, width, height)
    draw(canvas)
    requestLayout()
    return bitmap
}