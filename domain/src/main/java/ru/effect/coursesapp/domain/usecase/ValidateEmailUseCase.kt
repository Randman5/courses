package ru.effect.coursesapp.domain.usecase

class ValidateEmailUseCase {
    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}