package me.qidonk.footballapp.utils

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

    fun formatDateTimeToIndonesia(date: String?, time: String?): Date? {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        formatter.timeZone = TimeZone.getTimeZone("Asia/Jakarta")
        val dateTime = "$date $time"
        return formatter.parse(dateTime)
    }

    fun formatDate(dateTime: Date?): String {
        return SimpleDateFormat("EEE, dd MMM yyyy", Locale.ENGLISH).format(dateTime)
    }

    fun formatTime(dateTime: Date?): String {
        return SimpleDateFormat("HH:mm", Locale.ENGLISH).format(dateTime)
    }

}
