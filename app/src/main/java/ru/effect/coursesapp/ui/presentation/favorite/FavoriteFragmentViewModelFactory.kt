package ru.effect.coursesapp.ui.presentation.favorite


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.effect.coursesapp.domain.usecase.DeleteFromFavoriteUseCase
import ru.effect.coursesapp.domain.usecase.LoadCoursesLocalUseCase

class FavoriteFragmentViewModelFactory(
    val loadCoursesLocalUseCase: LoadCoursesLocalUseCase,
    val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoriteFragmentViewModel(loadCoursesLocalUseCase, deleteFromFavoriteUseCase) as T
    }
}