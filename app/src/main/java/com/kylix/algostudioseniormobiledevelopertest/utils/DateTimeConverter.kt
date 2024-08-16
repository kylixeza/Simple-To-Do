package com.kylix.algostudioseniormobiledevelopertest.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

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