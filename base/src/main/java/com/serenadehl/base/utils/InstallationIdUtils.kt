package com.serenadehl.base.utils

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.io.RandomAccessFile
import java.util.*

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-09-11 02:12:26
 */
object InstallationIdUtils {
    private const val INSTALLATION = "INSTALLATION"

    private var mId: String? = null

    fun getInstallationId(context: Context): String? {
        if (mId == null) {
            val file = File(context.filesDir, INSTALLATION)
            mId = if (file.exists()) {
                readInstallationId(file)
            } else {
                writeInstallationId(file)
            }
        }
        return mId
    }

    private fun readInstallationId(file: File): String? {
        val raf = RandomAccessFile(file, "r")
        val byteArray = ByteArray(raf.length().toInt())
        raf.readFully(byteArray)
        raf.close()
        return String(byteArray)
    }

    private fun writeInstallationId(file: File): String? {
        val fos = FileOutputStream(file)
        val id = UUID.randomUUID().toString()
        fos.write(id.toByteArray())
        fos.close()
        return id
    }
}