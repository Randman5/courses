package ru.effect.coursesapp.data.repository

import ru.effect.coursesapp.data.api.ApiService
import ru.effect.coursesapp.data.mapper.CourseMapper
import ru.effect.coursesapp.domain.models.CourseModel
import ru.effect.coursesapp.domain.repository.CoursesRepository

class CoursesRepositoryImpl(
    private val apiService: ApiService,
    private val coursesMapper: CourseMapper
) : CoursesRepository {

    override suspend fun getCourses(): List<CourseModel> {
        return apiService.getCourses().courses
            .map { coursesMapper.toDomain(it) }
    }

}