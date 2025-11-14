package ru.effect.coursesapp.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.effect.coursesapp.domain.models.CourseModel

interface CoursesRepositoryLocal {

    fun getAllCourses(): Flow<List<CourseModel>>
    suspend fun getCourseById(id: Int): CourseModel?
    suspend fun addCourseToFavorite(course: CourseModel)
    suspend fun deleteCourseFromFavorite(course: CourseModel)
}
