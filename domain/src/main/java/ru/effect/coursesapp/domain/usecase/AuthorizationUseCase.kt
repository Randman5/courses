package ru.effect.coursesapp.domain.usecase

import ru.effect.coursesapp.domain.models.UserModel
import ru.effect.coursesapp.domain.repository.SessionRepository

class AuthorizationUseCase(val sessionRepository: SessionRepository) {

    fun execute(user: UserModel): Boolean  {
        // TODO должен быть запрос на сервер - какая то авторизация
        if (user.email.isNotEmpty() || user.password.isNotEmpty()){
            sessionRepository.saveUser(user)
            return true
        }
        return false
    }
}