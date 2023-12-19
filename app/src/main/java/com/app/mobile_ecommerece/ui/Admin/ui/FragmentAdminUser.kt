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
import com.app.mobile_ecommerece.databinding.FragmentRoomAdminBinding
import com.app.mobile_ecommerece.databinding.FragmentUsersAdminBinding
import com.app.mobile_ecommerece.model.RoomModel
import com.app.mobile_ecommerece.model.UserAdminDataJson
import com.app.mobile_ecommerece.ui.Admin.adapter.RoomAdminAdapter
import com.app.mobile_ecommerece.ui.Admin.adapter.UserAdapter
import com.app.mobile_ecommerece.viewmodels.AdminViewModel

class FragmentAdminUser : BaseFragment<FragmentUsersAdminBinding>(true) {
    private val adminViewModel: AdminViewModel by activityViewModels()
    private val userAdapter: UserAdapter by lazy{
        UserAdapter(requireContext(), onItemClick)
    }
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentUsersAdminBinding {
        return FragmentUsersAdminBinding.inflate(inflater, container, false)
    }

    private fun setupRecycleViewLayout() {
        binding.rvUsers.adapter = userAdapter
        binding.rvUsers.layoutManager = GridLayoutManager(context, 1)
    }

    private fun observerEvent() {
        registerAllExceptionEvent(adminViewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(adminViewModel, viewLifecycleOwner)
        registerObserverNavigateEvent(adminViewModel, viewLifecycleOwner)
    }

    private fun setUpNavigate(){
        binding.imageButton.setOnClickListener {
            val action: NavDirections = FragmentAdminUserDirections.actionAdminUserFragmentToAdminManagerFragment()
            navigateAction(action)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.adminViewModel = adminViewModel
        observerEvent()
        setUpNavigate()
        setupRecycleViewLayout()
        adminViewModel.getALlUser()
        val controller = findNavController()
    }

    private val onItemClick: (UserAdminDataJson) -> Unit = {
        val action: NavDirections = FragmentAdminUserDirections.actionAdminUserFragmentToAdminUserInfoFragment(it)
        navigateAction(action)
    }
}