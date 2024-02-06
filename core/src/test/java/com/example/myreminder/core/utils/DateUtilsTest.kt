package com.example.myreminder.core.utils

import org.junit.Assert.*
import org.junit.Test
import java.time.LocalDateTime

class DateUtilsTest {
    @Test
    fun isPast() {
        val result1 = DateUtils.isPast("2020-11-11 23:55")
        val expected1 = true
        assertEquals(result1, expected1)

        val result2 = DateUtils.isPast("2023-07-01 15:20")
        val expected2 = true
        assertEquals(result2, expected2)

        val result3 = DateUtils.isPast("2050-07-01 15:20")
        val expected3 = false
        assertEquals(result3, expected3)
    }

    @Test
    fun convertToDateTime() {
        val result1 = DateUtils.convertToDateTime("2020-11-11 23:55")
        val expected1 = LocalDateTime.of(2020, 11, 11, 23, 55)
        assertEquals(result1, expected1)

        val result2 = DateUtils.convertToDateTime("2023-07-01 15:20")
        val expected2 = LocalDateTime.of(2023, 7, 1, 15, 20)
        assertEquals(result2, expected2)

        val result3 = DateUtils.convertToDateTime("2050-07-01 15:20")
        val expected3 = LocalDateTime.of(2050, 7, 1, 15, 20)
        assertEquals(result3, expected3)
    }
}