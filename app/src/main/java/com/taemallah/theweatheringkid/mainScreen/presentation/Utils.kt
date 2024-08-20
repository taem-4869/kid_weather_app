package com.taemallah.theweatheringkid.mainScreen.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun getFormattedDAte (date : LocalDateTime = LocalDateTime.now(), pattern : String = "eeee dd MMMM, hh:mma"): String{
    return date.format(DateTimeFormatter.ofPattern(pattern))
}