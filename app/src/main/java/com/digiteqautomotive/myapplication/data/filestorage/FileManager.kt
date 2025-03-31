package com.digiteqautomotive.myapplication.data.filestorage


import android.content.Context
import java.io.File
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

class FileManager(private val context: Context) {

    private val key: SecretKey = generateKey()
    private val iv = ByteArray(16) // IV should be securely stored

    fun writeEncryptedFile(fileName: String, data: String) {
        val file = File(context.filesDir, fileName)
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, key, IvParameterSpec(iv))

        val encryptedData = cipher.doFinal(data.toByteArray())
        file.writeBytes(encryptedData)
    }

    fun readEncryptedFile(fileName: String): String? {
        val file = File(context.filesDir, fileName)
        if (!file.exists()) return null

        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, key, IvParameterSpec(iv))

        return String(cipher.doFinal(file.readBytes()))
    }

    private fun generateKey(): SecretKey {
        val keyGenerator = KeyGenerator.getInstance("AES")
        keyGenerator.init(256)
        return keyGenerator.generateKey()
    }
}