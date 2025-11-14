package ru.effect.coursesapp.ui.presentation.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import ru.effect.coursesapp.R
import ru.effect.coursesapp.app.App
import ru.effect.coursesapp.databinding.FragmentLoginBinding
import javax.inject.Inject


class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    lateinit var viewModel: LoginFragmentViewModel

    @Inject
    lateinit var viewModelFactory: LoginFragmentViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)

        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[LoginFragmentViewModel::class]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (!::binding.isInitialized) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        }


        //Проверка авторизованного пользователя
        viewModel.isLoggedIn()
        viewModel.isLoggedInLive.observe(viewLifecycleOwner) {
            if (it == true) {
                navigateToHome()
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.authorizationStatusLive.observe(viewLifecycleOwner) {
            if (it) {
                navigateToHome()
            } else {
                Toast.makeText(context, R.string.forgot_email, Toast.LENGTH_LONG).show()
            }
        }
        viewModel.isValidEmailLive.observe(viewLifecycleOwner) {
            binding.entryButton.isEnabled = it
        }

        binding.entryButton.setOnClickListener {
            val email = binding.emailField.text.toString()
            val password = binding.passwordField.text.toString()
            viewModel.authorize(email, password)
        }

        binding.emailField.filters = arrayOf(noCyrillicFilter)

        //TODO может нудно ещё проверять пароль на количество символов (в тз ничего)
        binding.emailField.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(editable: Editable) {
                viewModel.validateEmail(editable.toString())
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        })

        binding.vkButton.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/"))
            startActivity(intent)
        }

        binding.okButton.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://ok.ru/"))
            startActivity(intent)
        }
    }

    private val noCyrillicFilter = InputFilter { source, _, _, _, _, _ ->
        if (source.matches(Regex(".*[а-яА-ЯёЁ]+.*"))) {
            "" // удаляем кириллицу
        } else {
            null // оставляем остальные символы
        }
    }

    private fun navigateToHome() {
        findNavController().navigate(R.id.homeFragment, null, NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setRestoreState(true)
            .setPopUpTo(R.id.loginFragment, true)
            .build())
    }
}