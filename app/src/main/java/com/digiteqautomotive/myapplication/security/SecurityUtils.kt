package com.digiteqautomotive.myapplication.security

import java.security.SecureRandom
import java.security.MessageDigest
import java.util.Base64

object SecurityUtils {
    fun generateSalt(): String {
        val salt = ByteArray(16)
        SecureRandom().nextBytes(salt)
        return Base64.getEncoder().encodeToString(salt)
    }

    fun hashPassword(password: String, salt: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashed = digest.digest((salt + password).toByteArray())
        return Base64.getEncoder().encodeToString(hashed)
    }
}
