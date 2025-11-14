package ru.effect.coursesapp.domain.usecase

import kotlinx.coroutines.flow.first
import ru.effect.coursesapp.domain.models.CourseModel
import ru.effect.coursesapp.domain.repository.CoursesRepository
import ru.effect.coursesapp.domain.repository.CoursesRepositoryLocal
import ru.effect.coursesapp.domain.sorting.SortOrder

class LoadCoursesUseCase(
    val coursesRepository: CoursesRepository,
    val coursesRepositoryLocal: CoursesRepositoryLocal
) {

    suspend fun execute(sortPublishDate: SortOrder?): List<CourseModel> {
        val courses = coursesRepository.getCourses()
        val localCourses = coursesRepositoryLocal.getAllCourses().first().map { it.id }
        courses.forEach {
            it.hasLike = localCourses.contains(it.id)
        }

        return when(sortPublishDate){
            SortOrder.ASC -> courses.sortedBy { sortPublishDate }
            SortOrder.DESC -> courses.sortedBy { sortPublishDate }.reversed()
            else -> courses.sortedBy { sortPublishDate }
        }
    }
}