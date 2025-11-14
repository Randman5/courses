package ru.effect.coursesapp.data.local.db.converters

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


/**
 * Вспомогательный класс для форматирования дат
 */
class DateConverter {

    @TypeConverter
    fun parseDate(date: String): Date {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return format.parse(date)!!
    }

    @TypeConverter
    fun dateToIso(date: Date): String {
        val isoFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return isoFormat.format(date)
    }
}