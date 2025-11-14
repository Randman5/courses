package ru.effect.coursesapp.data.repository

import ru.effect.coursesapp.data.mapper.UserMapper
import ru.effect.coursesapp.data.local.SharedPreferences.session.SessionStorage
import ru.effect.coursesapp.domain.models.UserModel
import ru.effect.coursesapp.domain.repository.SessionRepository


class SessionRepositoryImpl(
    private val storage: SessionStorage,
    private val userMapper: UserMapper
) : SessionRepository {

    override fun saveUser(user: UserModel) {
        val userDto = userMapper.toDto(user)
        storage.saveUser(userDto)
    }

    override fun getUser(): UserModel? {
        val userData = storage.getUser() ?: return null
        return userMapper.toEntity(userData)
    }

    override fun isUserLoggedIn(): Boolean {
        return storage.hasUser()
    }

    override fun clearUser() {
        storage.clearUser()
    }
}