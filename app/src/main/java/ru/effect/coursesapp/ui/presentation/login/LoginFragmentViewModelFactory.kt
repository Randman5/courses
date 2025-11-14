package ru.effect.coursesapp.ui.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.effect.coursesapp.domain.usecase.AuthorizationUseCase
import ru.effect.coursesapp.domain.usecase.IsLoggedInUseCase
import ru.effect.coursesapp.domain.usecase.ValidateEmailUseCase

class LoginFragmentViewModelFactory(
    val authorizationUseCase:AuthorizationUseCase,
    val validateEmailUseCase:ValidateEmailUseCase,
    val isLoggedInUseCase:IsLoggedInUseCase,
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginFragmentViewModel(authorizationUseCase, validateEmailUseCase, isLoggedInUseCase) as T
    }
}