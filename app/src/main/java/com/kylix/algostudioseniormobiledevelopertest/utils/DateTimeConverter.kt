package com.kylix.algostudioseniormobiledevelopertest.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

fun getCurrentDate(): String {
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    return currentDate.format(formatter)
}

fun String.convertToFormattedDate(): String {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val date = LocalDate.parse(this, formatter)
    return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
}

fun String.calculateDateDifference(): String {
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val targetFormat = DateTimeFormatter.ofPattern("dd MMM yyyy")

    val today = LocalDate.parse(getCurrentDate(), formatter)
    val date = LocalDate.parse(this, formatter)
    val gap = ChronoUnit.DAYS.between(today, date)
    val targetDate = date.format(targetFormat)

    return when {
        gap == 0L -> "Today ($targetDate)"
        gap == 1L -> "Tomorrow ($targetDate)"
        gap == -1L -> "Yesterday ($targetDate)"
        gap > 1 -> "${date.dayOfWeek.name} ($targetDate)"
        else -> ""
    }
}