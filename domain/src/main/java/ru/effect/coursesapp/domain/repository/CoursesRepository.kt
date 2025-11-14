package ru.effect.coursesapp.domain.repository

import ru.effect.coursesapp.domain.models.CourseModel

interface CoursesRepository {
    suspend fun getCourses(): List<CourseModel>
}