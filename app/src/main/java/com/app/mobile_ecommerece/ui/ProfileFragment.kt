package com.app.mobile_ecommerece.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.app.mobile_ecommerece.R
import com.app.mobile_ecommerece.base.BaseFragment
import com.app.mobile_ecommerece.databinding.FragmentHomeBinding
import com.app.mobile_ecommerece.databinding.FragmentProfileBinding
import com.app.mobile_ecommerece.model.ProductModel
import com.app.mobile_ecommerece.model.RoomModel
import com.app.mobile_ecommerece.ui.adapter.ProductAdapter
import com.app.mobile_ecommerece.ui.adapter.RoomAdapter
import com.app.mobile_ecommerece.viewmodels.HomeViewModel
import com.app.mobile_ecommerece.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(false) {
    private val userViewModel: UserViewModel by activityViewModels()
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater, container, false)
    }

    private fun observerEvent() {
        registerAllExceptionEvent(userViewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(userViewModel, viewLifecycleOwner)
        registerObserverNavigateEvent(userViewModel, viewLifecycleOwner)
    }

    private fun setUpNavigate(){
        binding.LayoutUserDetail.setOnClickListener {
            if(userViewModel.checkIsLogin()){
                val action: NavDirections = ProfileFragmentDirections.actionProfileFragmentToPersonalDetailFragment()
                navigateAction(action)
            }
        }
        binding.LayoutAddressShip.setOnClickListener {
            if(userViewModel.checkIsLogin()){
                val action: NavDirections = ProfileFragmentDirections.actionProfileFragmentToAddressListFragment()
                navigateAction(action)
            }
        }
        binding.LayoutMyOrderProfile.setOnClickListener {
            if(userViewModel.checkIsLogin()){
                val action: NavDirections = ProfileFragmentDirections.actionProfileFragmentToOrderListFragment()
                navigateAction(action)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        observerEvent()
        setUpNavigate()
        val controller = findNavController()
    }

}