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
import com.app.mobile_ecommerece.databinding.FragmentCategoryAdminBinding
import com.app.mobile_ecommerece.databinding.FragmentRoomAdminBinding
import com.app.mobile_ecommerece.model.CategoryModel
import com.app.mobile_ecommerece.model.RoomModel
import com.app.mobile_ecommerece.ui.Admin.adapter.CategoryAdminAdapter
import com.app.mobile_ecommerece.ui.Admin.adapter.RoomAdminAdapter
import com.app.mobile_ecommerece.viewmodels.AdminViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentAdminRoom : BaseFragment<FragmentRoomAdminBinding>(true) {
    private val adminViewModel: AdminViewModel by activityViewModels()
    private val roomAdapter: RoomAdminAdapter by lazy{
        RoomAdminAdapter(requireContext(), onItemClick)
    }
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRoomAdminBinding {
        return FragmentRoomAdminBinding.inflate(inflater, container, false)
    }

    private fun setupRecycleViewLayout() {
        binding.rvRoom.adapter = roomAdapter
        binding.rvRoom.layoutManager = GridLayoutManager(context, 2)
    }

    private fun observerEvent() {
        registerAllExceptionEvent(adminViewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(adminViewModel, viewLifecycleOwner)
        registerObserverNavigateEvent(adminViewModel, viewLifecycleOwner)
    }

    private fun setUpNavigate(){
        binding.imageButton.setOnClickListener {
            val action: NavDirections = FragmentAdminRoomDirections.actionAdminRoomFragmentToAdminManagerFragment()
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
        adminViewModel.getALlRoom()
        val controller = findNavController()
    }

    private val onItemClick: (RoomModel) -> Unit = {

    }
}