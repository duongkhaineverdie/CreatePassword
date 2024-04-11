package com.emenike.createpassword.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.core.content.ContextCompat

object Constants {
    const val LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz"
    val UPPERCASE_LETTERS = LOWERCASE_LETTERS.uppercase()
    const val DIGITS = "0123456789"
    const val SPECIAL_CHAR = "!@#$%^&*()_+-=[]{}|;:'<>,.?/"

    fun copyToClipboard(text: String, context: Context) {
        val clipboard = ContextCompat.getSystemService(context, ClipboardManager::class.java) as ClipboardManager
        clipboard.setPrimaryClip(ClipData.newPlainText("Copied Text", text)) // Correct usage
    }
}

