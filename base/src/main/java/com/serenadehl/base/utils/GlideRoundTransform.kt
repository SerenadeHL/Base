package com.serenadehl.base.utils

import android.content.Context
import android.graphics.*
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.serenadehl.base.extensions.dp
import java.security.MessageDigest

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-02-03 20:46:01
 */
class GlideRoundTransform(context: Context, dpValue: Float) : BitmapTransformation() {
    private var mRadius = context.dp(dpValue).toFloat()
    private val corners = arrayOf(true, true, true, true)

    companion object {
        private const val MAGIC_NUMBER = 0.551915024494F
    }

    fun enableLeftTop(enable: Boolean): GlideRoundTransform {
        corners[0] = enable
        return this
    }

    fun enableLeftBottom(enable: Boolean): GlideRoundTransform {
        corners[1] = enable
        return this
    }

    fun enableRightTop(enable: Boolean): GlideRoundTransform {
        corners[2] = enable
        return this
    }

    fun enableRightBottom(enable: Boolean): GlideRoundTransform {
        corners[3] = enable
        return this
    }

    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        return roundCrop(pool, toTransform)
    }

    private fun roundCrop(pool: BitmapPool, source: Bitmap): Bitmap {
        val width = source.width
        val height = source.height

        var result: Bitmap? = pool.get(width, height, Bitmap.Config.ARGB_8888)
        if (result == null) {
            result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        }

        val canvas = Canvas(result!!)
        val paint = Paint()
        paint.shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.isAntiAlias = true
        //利用三次贝塞尔曲线绘制各个圆角矩形
        val path = Path()

        if (corners[0]) {//画左上角的圆弧
            path.moveTo(0f, mRadius)
            path.cubicTo(0f, (1 - MAGIC_NUMBER) * mRadius, (1 - MAGIC_NUMBER) * mRadius, 0f, mRadius, 0f)
        } else {
            path.moveTo(0f, 0f)
        }

        if (corners[2]) {//画右上角的圆弧
            path.lineTo(width - mRadius, 0f)
            path.cubicTo(
                width - (1 - MAGIC_NUMBER) * mRadius,
                0f,
                width.toFloat(),
                (1 - MAGIC_NUMBER) * mRadius,
                width.toFloat(),
                mRadius
            )
        } else {
            path.lineTo(width.toFloat(), 0f)
        }

        if (corners[3]) {//画右下角的圆弧
            path.lineTo(width.toFloat(), height - mRadius)
            path.cubicTo(
                width.toFloat(),
                height - (1 - MAGIC_NUMBER) * mRadius,
                width - (1 - MAGIC_NUMBER) * mRadius,
                height.toFloat(),
                width - mRadius,
                height.toFloat()
            )
        } else {
            path.lineTo(width.toFloat(), height.toFloat())
        }

        if (corners[1]) {//画左下角的圆弧
            path.lineTo(mRadius, height.toFloat())
            path.cubicTo(
                (1 - MAGIC_NUMBER) * mRadius,
                height.toFloat(),
                0f,
                height - (1 - MAGIC_NUMBER) * mRadius,
                0f,
                height - mRadius
            )
        } else {
            path.lineTo(0f, height.toFloat())
        }

        path.close()
        canvas.drawPath(path, paint)

        return result
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update((javaClass.name + Math.round(mRadius)).toByteArray())
    }
}