package ru.effect.coursesapp.ui.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale


/**
 * Вспомогательный класс для форматирования дат
 */
object DateUtils {

    /**
     * Преобразует дату из формата "yyyy-MM-dd" в русский формат:
     * Например: "2024-01-20" -> "20 января 2024"
     */
    fun formatToRussian(date: Date): String {
        return try {
            val outputFormat = SimpleDateFormat("d MMMM yyyy", Locale("ru"))
            outputFormat.format(date)
        } catch (e: Exception) {
            // Если парсинг не удался — возвращаем исходную строку
            date.toString()
        }
    }

    fun parseDate(date: String): Date {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return format.parse(date)!!
    }
}