package ru.effect.coursesapp.domain.usecase

import ru.effect.coursesapp.domain.repository.SessionRepository

class IsLoggedInUseCase(val sessionRepository: SessionRepository) {

    fun execute(): Boolean{
        return sessionRepository.isUserLoggedIn()
    }
}