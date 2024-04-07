package com.thucthantinh.createpassword.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.core.content.ContextCompat
import com.thucthantinh.createpassword.data.model.DateTimeHomeDisplay
import com.thucthantinh.createpassword.data.model.MinuteAndHour
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Constants {
    const val LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz"
    val UPPERCASE_LETTERS = LOWERCASE_LETTERS.uppercase()
    const val DIGITS = "0123456789"
    const val SPECIAL_CHAR = "!@#$%^&*()_+-=[]{}|;:'<>,.?/"


    fun convertMillisToMinuteAndHour(timestamp: Long): MinuteAndHour {
        val localDateTime = Instant.fromEpochMilliseconds(timestamp)
            .toLocalDateTime(timeZone = TimeZone.currentSystemDefault())
        val minute = localDateTime.minute + localDateTime.second / 60f
        val hour = localDateTime.hour + localDateTime.minute / 60f
        val second = localDateTime.second
        return MinuteAndHour(minute, hour, second.toFloat())
    }


    fun convertMinuteToDegrees(minute: Float): Float {
        val result = ((minute % 60) / 60) * 360 - 180
        return result
    }

    fun convertHourToDegrees(hour: Float): Float {
        val result = ((hour % 12) / 12) * 360 - 180
        return result
    }

    fun copyToClipboard(text: String, context: Context) {
        val clipboard = ContextCompat.getSystemService(context, ClipboardManager::class.java) as ClipboardManager
        clipboard.setPrimaryClip(ClipData.newPlainText("Copied Text", text)) // Correct usage
    }
}

