package ru.effect.coursesapp.ui.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.effect.coursesapp.domain.models.UserModel
import ru.effect.coursesapp.domain.usecase.AuthorizationUseCase
import ru.effect.coursesapp.domain.usecase.IsLoggedInUseCase
import ru.effect.coursesapp.domain.usecase.ValidateEmailUseCase

class LoginFragmentViewModel(
    private val authorizationUseCase: AuthorizationUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val isLoggedInUseCase: IsLoggedInUseCase
): ViewModel() {

    private val authorizationStatusMutable = MutableLiveData<Boolean>()
    val authorizationStatusLive: LiveData<Boolean> = authorizationStatusMutable

    private val isValidEmailMutable = MutableLiveData<Boolean>()
    val isValidEmailLive: LiveData<Boolean> = isValidEmailMutable

    private val isLoggedInMutable = MutableLiveData<Boolean>()
    val isLoggedInLive: LiveData<Boolean> = isLoggedInMutable

    fun validateEmail(email: String) {
        isValidEmailMutable.value = validateEmailUseCase.isValidEmail(email)
    }

    fun authorize(email:String, password: String) {
        val isAuthorize = authorizationUseCase.execute(
            UserModel(
                email,
                password
            )
        )
        authorizationStatusMutable.value = isAuthorize
    }

    fun isLoggedIn() {
        isLoggedInMutable.value = isLoggedInUseCase.execute()
    }
}