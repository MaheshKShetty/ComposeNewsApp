package com.cap.samplecompose.helper

import android.os.Build
import android.text.TextUtils
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

val dateFormat=
    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'" , Locale.getDefault())

object Utils {
    var modifier = Modifier
        .fillMaxWidth()
}

fun getDateInRequiredFormat(
    dateInString: String?,
    formatter: SimpleDateFormat = SimpleDateFormat("dd/MM/yy, hh:mm a" , Locale.getDefault()),
    currentFormat: SimpleDateFormat? = dateFormat
): String {
    if (!TextUtils.isEmpty(dateInString)) {
        var date = Date()
        try {
            date = dateInString?.let { currentFormat?.parse(it) }!!
        } catch (e: ParseException) {
            return dateInString ?: ""
        }
        return formatter.format(date)
    }
    return ""
}