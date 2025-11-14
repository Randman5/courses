package ru.effect.coursesapp.data.api.response

import ru.effect.coursesapp.data.Dto.CourseDto

data class CoursesResponse(
    val courses: List<CourseDto>
)