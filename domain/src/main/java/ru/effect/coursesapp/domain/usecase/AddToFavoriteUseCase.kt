package ru.effect.coursesapp.domain.usecase

import ru.effect.coursesapp.domain.models.CourseModel
import ru.effect.coursesapp.domain.repository.CoursesRepositoryLocal

class AddToFavoriteUseCase(val courseRepositoryLocal: CoursesRepositoryLocal ) {

    suspend fun execute(courseModel: CourseModel) {
        courseRepositoryLocal.addCourseToFavorite(courseModel)
    }
}