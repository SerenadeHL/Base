package me.serenadehl.base.extensions

import java.io.File
import java.io.FileInputStream
import java.math.BigInteger
import java.security.MessageDigest

inline fun String.md5(): String {
    try {
        // Create MD5 Hash
        val digest = MessageDigest.getInstance("MD5")
        val messageDigest = digest.digest(this.toByteArray())
        // Create Hex String
        val hexString = StringBuffer()
        for (i in messageDigest.indices)
            hexString.append(Integer.toHexString(0xFF and messageDigest[i].toInt()))
        return hexString.toString()
    } catch (e: Exception) {
        throw e
    }
}

inline fun ByteArray.md5(): ByteArray {
    try {
        val md = MessageDigest.getInstance("MD5")
        return md.digest(this)
    } catch (e: Exception) {
        throw e
    }
}

inline fun File.md5(): String {
    if (!isFile) {
        return ""
    }

    val digest: MessageDigest
    try {
        digest = MessageDigest.getInstance("MD5")
        val fis = FileInputStream(this)
        val buffer = ByteArray(1024)
        var len = fis.read(buffer, 0, 1024)
        while (len != -1) {
            digest.update(buffer, 0, len)
            len = fis.read(buffer, 0, 1024)
        }
        fis.close()
    } catch (e: Exception) {
        e.printStackTrace()
        return ""
    }
    val bigInt = BigInteger(1, digest?.digest())
    return bigInt.toString(16)
}
