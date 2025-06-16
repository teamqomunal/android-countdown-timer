package com.qomunal.opensource.androidresearch.common.ext

import android.os.CountDownTimer
import android.widget.TextView
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


const val ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
const val DEFAULT_FORMAT = "dd LLLL yyyy"
const val DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss"

const val DATE_TIME_FORMAT_AM_PM = "dd MMMM yyyy, H.mm a"

const val SIMPLE_DATE_FORMAT = "dd-MM-yyyy"

val TIME_ZONE_ID_EXT = TimeZone.getDefault().id

// -------------------------------------------------------------------------------------------------

fun getDateTodayExt(format: String = DATE_TIME_FORMAT): String {
    return SimpleDateFormat(format, getLocaleExt()).format(Date())
}

fun String.dateFormater(
    from: String = SIMPLE_DATE_FORMAT,
    to: String = DATE_TIME_FORMAT,
): String {
    val fromParser = SimpleDateFormat(from, Locale.getDefault())
    val toParser = SimpleDateFormat(to, Locale.getDefault())
    return toParser.format(fromParser.parse(this) as Date)
}

fun String.dateFormaterToUTC(
    from: String = SIMPLE_DATE_FORMAT,
    to: String = DATE_TIME_FORMAT,
): String {
    val fromParser = SimpleDateFormat(from, Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone(TIME_ZONE_ID_EXT)
    }
    val toParser = SimpleDateFormat(to, Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }
    return if (this == "") {
        "-"
    } else {
        toParser.format(fromParser.parse(this) as Date)
    }
}

fun String.dateFormaterFromUTC(
    from: String = SIMPLE_DATE_FORMAT,
    to: String = DATE_TIME_FORMAT,
): String {
    val fromParser = SimpleDateFormat(from, Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }
    val toParser = SimpleDateFormat(to, Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone(TIME_ZONE_ID_EXT)
    }
    return if (this == "") {
        "-"
    } else {
        toParser.format(fromParser.parse(this) as Date)
    }
}

fun String.toIsoDateUTC(format: String = DATE_TIME_FORMAT): String {
    return if (this == "") {
        "-"
    } else {
        dateFormaterToUTC(from = format, to = ISO_FORMAT)
    }
}

fun String.fromIsoDateUTC(format: String = DATE_TIME_FORMAT): String {
    return if (this == "") {
        "-"
    } else {
        dateFormaterFromUTC(from = ISO_FORMAT, to = format)
    }
}

// -------------------------------------------------------------------------------------------------


fun String.getMillisExt(format: String = SIMPLE_DATE_FORMAT): Long {
    var date = Date()
    try {
        date = SimpleDateFormat(format, getLocaleExt()).apply {
            timeZone = TimeZone.getTimeZone(TIME_ZONE_ID_EXT)
        }.parse(this) ?: Date()
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return date.time
}


fun String.formatDateExt(to: String = DATE_TIME_FORMAT_AM_PM): String {
    return fromIsoDateUTC(format = to)
}


fun TextView.countdownTimerExt(millisInFuture: Long, countDownInterval: Long = 1000) {
    object : CountDownTimer(millisInFuture, countDownInterval) {
        override fun onTick(millisUntilFinished: Long) {
            // Used for formatting digits to be in 2 digits only
            val f: NumberFormat = DecimalFormat("00")
            val hour = (millisUntilFinished / 3600000) % 24
            val min = (millisUntilFinished / 60000) % 60
            val sec = (millisUntilFinished / 1000) % 60
            text = f.format(hour) + " Jam : " + f.format(min) + " Menit : " + f.format(sec) + " Detik"
        }

        override fun onFinish() {
            // When the task is over it will print 00:00:00
            text = "00 Jam : 00 Menit : 00 Detik"
        }
    }.start()
}