package me.serenadehl.base.extensions

import android.content.Context
import android.view.View
import androidx.annotation.DimenRes
import androidx.fragment.app.Fragment

//returns dp(dp) dimension value in pixels
inline fun Context.dp(value: Int): Int = (value * resources.displayMetrics.density).toInt()

inline fun Context.dp(value: Float): Int = (value * resources.displayMetrics.density).toInt()

//return sp dimension value in pixels
inline fun Context.sp(value: Int): Int = (value * resources.displayMetrics.scaledDensity).toInt()

inline fun Context.sp(value: Float): Int = (value * resources.displayMetrics.scaledDensity).toInt()

//converts px value into dp or sp
inline fun Context.px2dp(px: Int): Float = px.toFloat() / resources.displayMetrics.density

inline fun Context.px2sp(px: Int): Float = px.toFloat() / resources.displayMetrics.scaledDensity

inline fun Context.dimen(@DimenRes resource: Int): Int = resources.getDimensionPixelSize(resource)


//the same for the views
inline fun View.dp(value: Int): Int = context.dp(value)
inline fun View.dp(value: Float): Int = context.dp(value)
inline fun View.sp(value: Int): Int = context.sp(value)
inline fun View.sp(value: Float): Int = context.sp(value)
inline fun View.px2dp(px: Int): Float = context.px2dp(px)
inline fun View.px2sp(px: Int): Float = context.px2sp(px)
inline fun View.dimen(@DimenRes resource: Int): Int = context.dimen(resource)

//the same for Fragments
inline fun Fragment.dp(value: Int): Int? = activity?.dp(value)
inline fun Fragment.dp(value: Float): Int? = activity?.dp(value)
inline fun Fragment.sp(value: Int): Int? = activity?.sp(value)
inline fun Fragment.sp(value: Float): Int? = activity?.sp(value)
inline fun Fragment.px2dp(px: Int): Float? = activity?.px2dp(px)
inline fun Fragment.px2sp(px: Int): Float? = activity?.px2sp(px)
inline fun Fragment.dimen(@DimenRes resource: Int): Int? = activity?.dimen(resource)