package ru.effect.coursesapp.ui.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.effect.coursesapp.domain.models.CourseModel
import ru.effect.coursesapp.domain.sorting.SortOrder
import ru.effect.coursesapp.domain.usecase.AddToFavoriteUseCase
import ru.effect.coursesapp.domain.usecase.DeleteFromFavoriteUseCase
import ru.effect.coursesapp.domain.usecase.LoadCoursesUseCase

class HomeFragmentViewModel(
    val loadCoursesUseCase: LoadCoursesUseCase,
    val addToFavoriteUseCase: AddToFavoriteUseCase,
    val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase
) : ViewModel() {

    private val coursesMutable = MutableLiveData<List<CourseModel>>()
    val coursesLive: LiveData<List<CourseModel>> = coursesMutable

    private val errorMutable = MutableLiveData<String>()
    val errorLive: LiveData<String> = errorMutable

    private val showLoaderMutable = MutableLiveData<Boolean>(true)
    val showLoaderLive: LiveData<Boolean> = showLoaderMutable

    private val sortOrderMutable = MutableLiveData<SortOrder>(SortOrder.ASC)
    val sortLive: LiveData<SortOrder> = sortOrderMutable

    init {
        loadCourses()
    }

    fun loadCourses() {
        viewModelScope.launch {
            showLoaderMutable.postValue(true)
            try {
                val courses = loadCoursesUseCase.execute(sortOrderMutable.value)
                coursesMutable.postValue(courses)
                showLoaderMutable.postValue(false)
            } catch (e: Exception) {
                errorMutable.postValue(e.message)
            }
        }
    }

    fun addCourseToFavorite(userModel: CourseModel) {
        viewModelScope.launch {
            try {
                addToFavoriteUseCase.execute(userModel)
            } catch (e: Exception) {
                errorMutable.postValue(e.message)
            }
        }
    }

    fun deleteCourseFromFavorite(userModel: CourseModel) {
        viewModelScope.launch {
            try {
                deleteFromFavoriteUseCase.execute(userModel)
            } catch (e: Exception) {
                errorMutable.postValue(e.message)
            }
        }
    }

    fun changeSort() {
        sortOrderMutable.value = if (sortOrderMutable.value == SortOrder.ASC){
            SortOrder.DESC
        } else {
            SortOrder.ASC
        }
        loadCourses()
    }

}