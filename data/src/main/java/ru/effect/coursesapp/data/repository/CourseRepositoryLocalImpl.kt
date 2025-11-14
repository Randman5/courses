package ru.effect.coursesapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.effect.coursesapp.data.local.db.dao.CourseDao
import ru.effect.coursesapp.data.mapper.CourseMapper
import ru.effect.coursesapp.domain.models.CourseModel
import ru.effect.coursesapp.domain.repository.CoursesRepositoryLocal

class CourseRepositoryLocalImpl(
    private val dao: CourseDao,
    private val coursesMapper: CourseMapper
) : CoursesRepositoryLocal {

    override fun getAllCourses(): Flow<List<CourseModel>> =
        dao.getAllCourses().map { list -> list.map { coursesMapper.fromEntity(it) } }

    override suspend fun addCourseToFavorite(course: CourseModel) {
        val entity = coursesMapper.toEntity(course)
        return dao.insertCourse(entity)
    }

    override suspend fun deleteCourseFromFavorite(course: CourseModel) {
        return dao.deleteCourseById(course.id)
    }

    override suspend fun getCourseById(id: Int): CourseModel? {
        return dao.getCourseById(id)?.let { coursesMapper.fromEntity(it) }
    }

}