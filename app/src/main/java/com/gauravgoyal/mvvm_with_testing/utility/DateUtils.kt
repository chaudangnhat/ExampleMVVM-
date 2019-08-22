package com.gauravgoyal.mvvm_with_testing.utility

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

class DateUtils {
    companion object {
        @SuppressLint("SimpleDateFormat")
        fun convertToDateString(date: String): String {
            return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'").parse(date))
        }
    }
}