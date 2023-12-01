package com.app.mobile_ecommerece.ui

import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.app.mobile_ecommerece.base.BaseFragment
import com.app.mobile_ecommerece.databinding.FragmentPersonalDetailsBinding
import com.app.mobile_ecommerece.databinding.FragmentProfileBinding
import com.app.mobile_ecommerece.model.Request.ProfileRequest
import com.app.mobile_ecommerece.viewmodels.HomeViewModel
import com.app.mobile_ecommerece.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonalDetailFragment : BaseFragment<FragmentPersonalDetailsBinding>(true) {

    private val userViewModel: UserViewModel by activityViewModels()
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPersonalDetailsBinding {
        return FragmentPersonalDetailsBinding.inflate(inflater, container, false)
    }

    private fun observerEvent() {
        registerAllExceptionEvent(userViewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(userViewModel, viewLifecycleOwner)
        registerObserverNavigateEvent(userViewModel, viewLifecycleOwner)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.userViewModel = userViewModel
        observerEvent()
        val controller = findNavController()
        userViewModel.getProfile()
        binding.ibBack.setOnClickListener {
            navigateBack()
        }
        binding.btnEditSaveProfileDetail.setOnClickListener {
            updateProfile()
        }
    }

    fun updateProfile(){
        val profileRequest = ProfileRequest(binding.etFirstNameProfileDetail.text.toString(), binding.etLastNameProfileDetail.text.toString(),
        binding.etEmailProfileDetail.text.toString(), binding.etPhoneNumberProfileDetail.text.toString())
        userViewModel.updateProfile(profileRequest)
    }
}