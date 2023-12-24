package com.app.mobile_ecommerece.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.app.mobile_ecommerece.R
import com.app.mobile_ecommerece.base.BaseFragment
import com.app.mobile_ecommerece.databinding.FragmentChangePasswordBinding
import com.app.mobile_ecommerece.databinding.FragmentForgotPasswordBinding
import com.app.mobile_ecommerece.model.Request.ResetPassRequest
import com.app.mobile_ecommerece.viewmodels.UserViewModel
import io.github.muddz.styleabletoast.StyleableToast

class FragmentForgotPassword : BaseFragment<FragmentForgotPasswordBinding>(true) {
    private val userViewModel: UserViewModel by activityViewModels()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentForgotPasswordBinding {
        return FragmentForgotPasswordBinding.inflate(inflater, container, false)
    }

    private fun setupRecycleViewLayout() {

    }

    private fun observerEvent() {
        registerAllExceptionEvent(userViewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(userViewModel, viewLifecycleOwner)
        registerObserverNavigateEvent(userViewModel, viewLifecycleOwner)
    }

    private fun setUpNavigate(){

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        observerEvent()
        setUpNavigate()
        setupRecycleViewLayout()
        binding.btnReset.setOnClickListener {
            changePassword()
        }
        binding.btnSend.setOnClickListener {
            sendOTP()
        }

        userViewModel.isResetSuccess.observe(viewLifecycleOwner) { value ->
            if(value == true){
                StyleableToast.makeText(requireContext(), "Cập nhật password thành công!", Toast.LENGTH_LONG, R.style.SuccessToast).show()
                navigateToPage(R.id.action_forgotPasswordFragment_to_loginFragment)
            }
        }

        val controller = findNavController()
    }

    fun changePassword(){
        var pass = binding.etPass.text.toString()
        var cfpass = binding.etCfPass.text.toString()
        var email = binding.etEmail.text.toString()
        var otp = binding.etOTP.text.toString()
        if(pass == "" || cfpass == "" || email == "" || otp == ""){
            StyleableToast.makeText(requireContext(), "Vui lòng kiểm tra lại các thông tin", Toast.LENGTH_LONG, R.style.ErrorToast).show()
            return
        }
        if(isValidEmail(email) == false){
            StyleableToast.makeText(requireContext(), "Vui lòng kiểm tra lại email", Toast.LENGTH_LONG, R.style.ErrorToast).show()
            return
        }
        if(pass.length > 8){
            StyleableToast.makeText(requireContext(), "Nhập ít nhất 8 kí tự", Toast.LENGTH_LONG, R.style.ErrorToast).show()
        }
        else{
            if(pass == cfpass){
                var request = ResetPassRequest(otp, email, pass)
                userViewModel.resetPassword(request)
            }
            else{
                StyleableToast.makeText(requireContext(), "Vui lòng kiểm tra lại các thông tin", Toast.LENGTH_LONG, R.style.ErrorToast).show()
            }
        }
    }

    fun sendOTP(){
        var email = binding.etEmail.text.toString()
        if(isValidEmail(email)){
            userViewModel.sendOTP(email)
            StyleableToast.makeText(requireContext(), "OTP đã được gửi tới mail của bạn", Toast.LENGTH_LONG, R.style.SuccessToast).show()
        }
        else{
            StyleableToast.makeText(requireContext(), "Vui lòng kiểm tra lại email", Toast.LENGTH_LONG, R.style.ErrorToast).show()
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}