package ru.effect.coursesapp.ui.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.effect.coursesapp.R
import ru.effect.coursesapp.databinding.FragmentFavoriteBinding
import ru.effect.coursesapp.databinding.FragmentProfileBinding
import ru.effect.coursesapp.domain.models.CourseModel
import ru.effect.coursesapp.domain.models.UserModel
import ru.effect.coursesapp.ui.adapters.MainAdapter
import ru.effect.coursesapp.ui.adapters.delegates.HomeCoursesDelegateAdapter
import java.text.SimpleDateFormat
import java.util.Locale

class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        val adapter = MainAdapter(
            listOf(
                HomeCoursesDelegateAdapter(),
            )
        )
        binding.coursesRV.adapter = adapter
        binding.coursesRV.layoutManager = LinearLayoutManager(requireContext())

        adapter.setItems(
            listOf(
                CourseModel(
                    id = 100,
                    title = "Java-разработчик с нуля",
                    text = "Освойте backend-разработку и программирование на Java, фреймворки Spring и Maven, работу с базами данных и API. Создайте свой собственный проект, собрав портфолио и став востребованным специалистом для любой IT компании.",
                    price = "999",
                    rate = "4.9",
                    startDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse("2024-05-22")!!,
                    hasLike = false,
                    publishDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse("2024-02-02")!!
                ),
                CourseModel(
                    id = 101,
                    title = "3D-дженералист",
                    text = "Освой профессию 3D-дженералиста и стань универсальным специалистом, который умеет создавать 3D-модели, текстуры и анимации, а также может строить карьеру в геймдеве, кино, рекламе или дизайне.",
                    price = "12 000",
                    rate = "3.9",
                    startDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse("2024-09-10")!!,
                    hasLike = false,
                    publishDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse("2024-01-20")!!
                ),
                CourseModel(
                    id = 102,
                    title = "Python Advanced. Для продвинутых",
                    text = "Вы узнаете, как разрабатывать гибкие и высокопроизводительные серверные приложения на языке Kotlin. Преподаватели на вебинарах покажут пример того, как разрабатывается проект маркетплейса: от идеи и постановки задачи – до конечного решения",
                    price = "1 299",
                    rate = "4.3",
                    startDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse("2024-10-12")!!,
                    hasLike = true,
                    publishDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse("2024-08-10")!!
                )
            )
        )

        return binding.root
    }
}