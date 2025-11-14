package ru.effect.coursesapp.ui.presentation.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.effect.coursesapp.data.mapper.UserMapper
import ru.effect.coursesapp.data.repository.SessionRepositoryImpl
import ru.effect.coursesapp.domain.usecase.AddToFavoriteUseCase
import ru.effect.coursesapp.domain.usecase.AuthorizationUseCase
import ru.effect.coursesapp.domain.usecase.DeleteFromFavoriteUseCase
import ru.effect.coursesapp.domain.usecase.IsLoggedInUseCase
import ru.effect.coursesapp.domain.usecase.LoadCoursesUseCase
import ru.effect.coursesapp.domain.usecase.ValidateEmailUseCase

class HomeFragmentViewModelFactory(
    val loadCoursesUseCase: LoadCoursesUseCase,
    val addToFavoriteUseCase: AddToFavoriteUseCase,
    val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeFragmentViewModel(loadCoursesUseCase, addToFavoriteUseCase, deleteFromFavoriteUseCase) as T
    }
}