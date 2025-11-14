package ru.effect.coursesapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.effect.coursesapp.domain.models.CourseModel
import ru.effect.coursesapp.domain.repository.CoursesRepositoryLocal

class LoadCoursesLocalUseCase(val coursesLocalRepository: CoursesRepositoryLocal) {

    fun execute(): Flow<List<CourseModel>> {
        return coursesLocalRepository.getAllCourses()
    }
}