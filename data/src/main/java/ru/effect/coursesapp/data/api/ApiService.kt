package ru.effect.coursesapp.data.api

import retrofit2.http.GET
import ru.effect.coursesapp.data.Dto.CourseDto
import ru.effect.coursesapp.data.api.response.CoursesResponse

interface ApiService {
    @GET("/u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download")
    suspend fun getCourses(): CoursesResponse
}