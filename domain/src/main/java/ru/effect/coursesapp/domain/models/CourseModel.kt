package ru.effect.coursesapp.domain.models

import java.util.Date


data class CourseModel(
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: Date,
    var hasLike: Boolean,
    val publishDate: Date
)
