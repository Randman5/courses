package ru.effect.coursesapp.ui.utils

import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Вспомогательный класс для форматирования дат
 */
object DateUtils {

    /**
     * Преобразует дату из формата "yyyy-MM-dd" в русский формат:
     * Например: "2024-01-20" -> "20 января 2024"
     */
    fun formatToRussian(dateString: String): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat = SimpleDateFormat("d MMMM yyyy", Locale("ru"))
            val date = inputFormat.parse(dateString)
            outputFormat.format(date!!)
        } catch (e: Exception) {
            // Если парсинг не удался — возвращаем исходную строку
            dateString
        }
    }
}