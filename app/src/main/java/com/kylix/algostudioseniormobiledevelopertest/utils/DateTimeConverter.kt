package com.kylix.algostudioseniormobiledevelopertest.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

fun getCurrentDate(
    pattern: String = "dd-MM-yyyy"
): String {
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return currentDate.format(formatter)
}

fun String.convertToTextFieldDate(): String {
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val date = LocalDate.parse(this, formatter)
    val day = if (date.dayOfMonth < 10) "0${date.dayOfMonth}" else "${date.dayOfMonth}"
    val month = if (date.monthValue < 10) "0${date.monthValue}" else "${date.monthValue}"
    return "$day/$month/${date.year}"
}

fun String.convertToFormattedDate(): String {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val date = LocalDate.parse(this, formatter)
    return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
}

fun String.calculateDateDifference(): String {
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val targetFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy")

    val today = LocalDate.parse(getCurrentDate(), formatter)
    val date = LocalDate.parse(this, formatter)
    val gap = ChronoUnit.DAYS.between(today, date)
    val targetDate = date.format(targetFormat)

    return when {
        gap == 0L -> "Today ($targetDate)"
        gap == 1L -> "Tomorrow ($targetDate)"
        gap == -1L -> "Yesterday ($targetDate)"
        gap > 1 -> {
            val day = date.dayOfWeek.name.lowercase().replaceFirstChar {
                if (it.isLowerCase()) it.titlecase() else it.toString()
            }
            "$day ($targetDate)"
        }
        else -> ""
    }
}