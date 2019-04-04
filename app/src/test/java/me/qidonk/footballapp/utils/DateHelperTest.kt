package me.qidonk.footballapp.utils

import me.qidonk.footballapp.utils.DateHelper.formatDate
import me.qidonk.footballapp.utils.DateHelper.formatDateTimeToIndonesia
import me.qidonk.footballapp.utils.DateHelper.formatTime
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class DateHelperTest {

    @Test
    fun formatDateTimeToIndonesiaTest() {
        val date = "2019-01-01"
        val time = "00:00:00+00:00"
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        formatter.timeZone = TimeZone.getTimeZone("Asia/Jakarta")
        val dateTime = "$date $time"
        val dates: Date? = formatter.parse(dateTime)
        assertEquals(dates, formatDateTimeToIndonesia(date, time))
    }

    @Test
    fun formatDateTest() {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        val dateTime: Date? = dateFormat.parse("2019-01-01 00:00:00")
        assertEquals("Tue, 01 Jan 2019", formatDate(dateTime))
    }

    @Test
    fun formatTimeTest() {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        val dateTime: Date? = dateFormat.parse("2019-01-01 00:00:00")
        assertEquals("00:00", formatTime(dateTime))
    }
}