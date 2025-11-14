package ru.effect.coursesapp.ui.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.effect.coursesapp.R
import ru.effect.coursesapp.app.App
import ru.effect.coursesapp.databinding.FragmentFavoriteBinding
import ru.effect.coursesapp.ui.adapters.MainAdapter
import ru.effect.coursesapp.ui.adapters.delegates.HomeCoursesDelegateAdapter
import ru.effect.coursesapp.ui.presentation.home.HomeFragmentViewModel
import ru.effect.coursesapp.ui.presentation.home.HomeFragmentViewModelFactory
import javax.inject.Inject


class FavoriteFragment : Fragment() {

    lateinit var binding: FragmentFavoriteBinding

    lateinit var viewModel: FavoriteFragmentViewModel

    @Inject
    lateinit var viewModelFactory: FavoriteFragmentViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[FavoriteFragmentViewModel::class]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)

        val adapter = MainAdapter(
            listOf(
                HomeCoursesDelegateAdapter(
                    deleteFromFavorite = viewModel::deleteCourseFromFavorite
                ),
            )
        )
        binding.coursesRV.layoutManager = LinearLayoutManager(context)
        binding.coursesRV.adapter = adapter

        viewModel.getCoursesAsLiveData().observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }

        return binding.root
    }
}