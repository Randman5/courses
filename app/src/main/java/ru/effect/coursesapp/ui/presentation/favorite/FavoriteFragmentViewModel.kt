package ru.effect.coursesapp.ui.presentation.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.effect.coursesapp.domain.models.CourseModel
import ru.effect.coursesapp.domain.usecase.AddToFavoriteUseCase
import ru.effect.coursesapp.domain.usecase.DeleteFromFavoriteUseCase
import ru.effect.coursesapp.domain.usecase.LoadCoursesLocalUseCase
import ru.effect.coursesapp.domain.usecase.LoadCoursesUseCase

class FavoriteFragmentViewModel(
    val loadCoursesLocalUseCase: LoadCoursesLocalUseCase,
    val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase
) : ViewModel() {

    private val errorMutable = MutableLiveData<String>()
    val errorLive: LiveData<String> = errorMutable

    private val showLoaderMutable = MutableLiveData<Boolean>(true)
    val showLoaderLive: LiveData<Boolean> = showLoaderMutable

    private val courses: StateFlow<List<CourseModel>> =
        loadCoursesLocalUseCase.execute()
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly, // сразу начинает эмитить
                emptyList()
            )

    fun getCoursesAsLiveData() = courses.asLiveData()

    fun deleteCourseFromFavorite(userModel: CourseModel) {
        viewModelScope.launch {
            try {
                deleteFromFavoriteUseCase.execute(userModel)
            } catch (e: Exception) {
                errorMutable.postValue(e.message)
            }
        }
    }

}