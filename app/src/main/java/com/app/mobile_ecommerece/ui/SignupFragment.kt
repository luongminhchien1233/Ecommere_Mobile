package com.app.mobile_ecommerece.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.app.mobile_ecommerece.R
import com.app.mobile_ecommerece.base.BaseFragment
import com.app.mobile_ecommerece.databinding.FragmentSignupBinding
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
            if(binding.tvEmail.text.toString().isEmpty() || binding.tvPassword.text.toString().isEmpty() || binding.tvUserName.text.toString().isEmpty()){
                showErrorMessage("Missing Infomation !!")
            }

            if (binding.tvPassword.text.toString() != binding.tvConfirmPw.text.toString()) {
                StyleableToast.makeText(requireContext(), "Password not match", R.style.Theme_EcommerceApp).show()
            } else {
                SignUp();
            }
        }
    }

    private  fun SignUp(){

    }
}