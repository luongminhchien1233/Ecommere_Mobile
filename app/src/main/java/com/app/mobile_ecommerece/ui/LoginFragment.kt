package com.app.mobile_ecommerece.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.app.mobile_ecommerece.R
import com.app.mobile_ecommerece.base.BaseFragment
import com.app.mobile_ecommerece.databinding.FragmentLoginBinding
import com.app.mobile_ecommerece.model.Request.LoginRequest
import com.app.mobile_ecommerece.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(true) {

    private val userViewModel: UserViewModel by viewModels()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        registerAllExceptionEvent(userViewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(userViewModel, viewLifecycleOwner)
        registerObserverNavigateEvent(userViewModel, viewLifecycleOwner)

        binding.tvCreateAccount.setOnClickListener {
            navigateToPage(R.id.action_loginFragment_to_signupFragment)
        }

        binding.btnLogin.setOnClickListener {
            //Nếu email hoặc password rỗng thì thông báo
            if (binding.etEmailSignIn.text.toString().isEmpty() || binding.etPasswordSignIn.text.toString()
                    .isEmpty()
            ) {
                showErrorMessage("Invalid Email or Password!!")
            } else {
                Login()
            }
        }
    }

    private fun Login() {
        val loginRequest =
            LoginRequest(binding.etEmailSignIn.text.toString(), binding.etPasswordSignIn.text.toString())
        val isChecked = binding.cbRemember.isChecked
        userViewModel.login(loginRequest, isChecked)
    }
}