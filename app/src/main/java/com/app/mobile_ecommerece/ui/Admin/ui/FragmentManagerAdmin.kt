package com.app.mobile_ecommerece.ui.Admin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.app.mobile_ecommerece.base.BaseFragment
import com.app.mobile_ecommerece.databinding.FragmentAddressListBinding
import com.app.mobile_ecommerece.databinding.FragmentManagerAdminBinding
import com.app.mobile_ecommerece.model.AddressModel
import com.app.mobile_ecommerece.ui.AddressListFragmentDirections
import com.app.mobile_ecommerece.ui.ProfileFragmentDirections
import com.app.mobile_ecommerece.ui.adapter.AddressAdapter
import com.app.mobile_ecommerece.viewmodels.AddressViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentManagerAdmin : BaseFragment<FragmentManagerAdminBinding>(true) {
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentManagerAdminBinding {
        return FragmentManagerAdminBinding.inflate(inflater, container, false)
    }

    private fun setupRecycleViewLayout() {

    }

    private fun observerEvent() {

    }

    private fun setUpNavigate(){
        binding.imageButton.setOnClickListener {
            val action: NavDirections = FragmentManagerAdminDirections.actionAdminManagerFragmentToProfileFragment()
            navigateAction(action)
        }
        binding.LayoutCategory.setOnClickListener {
            val action: NavDirections = FragmentManagerAdminDirections.actionAdminManagerFragmentToAdminCategorytFragment()
            navigateAction(action)
        }
        binding.LayoutRoom.setOnClickListener {
            val action: NavDirections = FragmentManagerAdminDirections.actionAdminManagerFragmentToAdminRoomFragment()
            navigateAction(action)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        observerEvent()
        setUpNavigate()
        setupRecycleViewLayout()
        val controller = findNavController()
    }
}