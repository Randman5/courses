package ru.effect.coursesapp.ui.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.effect.coursesapp.R
import ru.effect.coursesapp.app.App
import ru.effect.coursesapp.databinding.FragmentHomeBinding
import ru.effect.coursesapp.domain.models.CourseModel
import ru.effect.coursesapp.ui.adapters.MainAdapter
import ru.effect.coursesapp.ui.adapters.delegates.HomeCoursesDelegateAdapter
import javax.inject.Inject

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var viewModel: HomeFragmentViewModel

    @Inject
    lateinit var viewModelFactory: HomeFragmentViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[HomeFragmentViewModel::class]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        val adapter = MainAdapter(
            listOf(
                HomeCoursesDelegateAdapter(
                    viewModel::addCourseToFavorite,
                    viewModel::deleteCourseFromFavorite
                ),
            )
        )

        binding.coursesRV.layoutManager = LinearLayoutManager(context)
        binding.coursesRV.adapter = adapter

        viewModel.coursesLive.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }
        viewModel.errorLive.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.showLoaderLive.observe(viewLifecycleOwner) {
            binding.loader.isVisible = it
        }

        binding.sortBtn.setOnClickListener{
            viewModel.changeSort()
        }

        return binding.root
    }

}