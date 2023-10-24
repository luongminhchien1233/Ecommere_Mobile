package com.app.mobile_ecommerece.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.app.mobile_ecommerece.R
import com.app.mobile_ecommerece.base.BaseFragment
import com.app.mobile_ecommerece.databinding.FragmentSignupBinding
import com.app.mobile_ecommerece.model.SignupRequest
import com.app.mobile_ecommerece.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.github.muddz.styleabletoast.StyleableToast

@AndroidEntryPoint
class SignupFragment : BaseFragment<FragmentSignupBinding>(true) {

    private val userViewModel: UserViewModel by viewModels()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignupBinding {
        return FragmentSignupBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        registerAllExceptionEvent(userViewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(userViewModel, viewLifecycleOwner)
        registerObserverNavigateEvent(userViewModel, viewLifecycleOwner)

        binding.btnBackSignUp.setOnClickListener {
            navigateToPage(R.id.action_signupFragment_to_loginFragment)
        }

        binding.btnSignUp.setOnClickListener {
            if(binding.etEmail.text.toString().isEmpty() || binding.etPasswordSignUp.text.toString().isEmpty() || binding.etUserName.text.toString().isEmpty()
                || binding.etPhoneNumber.text.toString().isEmpty() || binding.etFirstName.text.toString().isEmpty() || binding.etLastName.text.toString().isEmpty()){
                showErrorMessage("Missing Infomation !!")
            }

            if (binding.etConfirmPwSignUp.text.toString() != binding.etPasswordSignUp.text.toString()) {
                StyleableToast.makeText(requireContext(), "Password not match", R.style.Theme_EcommerceApp).show()
            } else {
                SignUp();
            }
        }
    }

    private  fun SignUp(){
        val signupRequest =
            SignupRequest(binding.etFirstName.text.toString(), binding.etLastName.text.toString(), binding.etUserName.text.toString(), binding.etPasswordSignUp.text.toString(), binding.etEmail.text.toString(), binding.etPhoneNumber.text.toString());

        userViewModel.signup(signupRequest)
    }
}