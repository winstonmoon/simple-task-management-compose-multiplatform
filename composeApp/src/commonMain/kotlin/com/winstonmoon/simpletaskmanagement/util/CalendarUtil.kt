package com.winstonmoon.simpletaskmanagement.util


import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn

fun getWeekDates(today: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())): List<LocalDate> {
    val weekDates = mutableListOf<LocalDate>()
    val orderedDaysOfWeek = listOf(
        DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY
    )

    val todayIndex = orderedDaysOfWeek.indexOf(today.dayOfWeek)

    val daysToGoBackToSunday = todayIndex.toLong()

    val sundayOfThisWeek = today.minus(daysToGoBackToSunday, DateTimeUnit.DAY)

    for (i in 0 until 7) {
        weekDates.add(sundayOfThisWeek.plus(i.toLong(), DateTimeUnit.DAY))
    }

    return weekDates
}