package com.app.mobile_ecommerece.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.app.mobile_ecommerece.R
import com.app.mobile_ecommerece.base.BaseFragment
import com.app.mobile_ecommerece.databinding.FragmentChangePasswordBinding
import com.app.mobile_ecommerece.viewmodels.UserViewModel
import io.github.muddz.styleabletoast.StyleableToast

class FragmentChangePassword : BaseFragment<FragmentChangePasswordBinding>(true) {
    private val userViewModel: UserViewModel by activityViewModels()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChangePasswordBinding {
        return FragmentChangePasswordBinding.inflate(inflater, container, false)
    }

    private fun setupRecycleViewLayout() {

    }

    private fun observerEvent() {
        registerAllExceptionEvent(userViewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(userViewModel, viewLifecycleOwner)
        registerObserverNavigateEvent(userViewModel, viewLifecycleOwner)
    }

    private fun setUpNavigate(){
        binding.imageButton.setOnClickListener {
            navigateBack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        observerEvent()
        setUpNavigate()
        setupRecycleViewLayout()
        binding.btnEditSave.setOnClickListener {
            changePassword()
        }
        val controller = findNavController()
    }

    fun changePassword(){
        var pass = binding.etPass.text.toString()
        var cfpass = binding.etCfPass.text.toString()
        if(pass.length > 8){
            StyleableToast.makeText(requireContext(), "Nhập ít nhất 8 kí tự", Toast.LENGTH_LONG, R.style.ErrorToast).show()
        }
        else{
            if(pass == cfpass){
                userViewModel.changePassword(pass)
                binding.etPass.text.clear()
                binding.etCfPass.text.clear()
                StyleableToast.makeText(requireContext(), "Update Password Successfully", Toast.LENGTH_LONG, R.style.SuccessToast).show()
            }
            else{
                StyleableToast.makeText(requireContext(), "Vui lòng kiểm tra lại các thông tin", Toast.LENGTH_LONG, R.style.ErrorToast).show()
            }
        }
    }
}