package ru.effect.coursesapp.data.local.SharedPreferences.session

import ru.effect.coursesapp.data.Dto.UserDto

interface SessionStorage {
    fun saveUser(userDto: UserDto)
    fun getUser(): UserDto?
    fun clearUser()
    fun hasUser(): Boolean
}