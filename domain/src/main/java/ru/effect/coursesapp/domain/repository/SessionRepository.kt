package ru.effect.coursesapp.domain.repository

import ru.effect.coursesapp.domain.models.UserModel

interface SessionRepository {

    fun saveUser(user: UserModel)
    fun getUser(): UserModel?
    fun isUserLoggedIn(): Boolean
    fun clearUser()
}