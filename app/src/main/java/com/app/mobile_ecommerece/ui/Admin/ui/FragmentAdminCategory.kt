package com.app.mobile_ecommerece.ui.Admin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.app.mobile_ecommerece.base.BaseFragment
import com.app.mobile_ecommerece.databinding.FragmentCategoryAdminBinding
import com.app.mobile_ecommerece.model.AddressModel
import com.app.mobile_ecommerece.model.CategoryModel
import com.app.mobile_ecommerece.ui.AddressListFragmentDirections
import com.app.mobile_ecommerece.ui.Admin.adapter.CategoryAdminAdapter
import com.app.mobile_ecommerece.ui.adapter.AddressAdapter
import com.app.mobile_ecommerece.viewmodels.AdminViewModel
import com.app.mobile_ecommerece.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class FragmentAdminCategory : BaseFragment<FragmentCategoryAdminBinding>(true) {
    private val adminViewModel: AdminViewModel by activityViewModels()
    private val categoryAdapter: CategoryAdminAdapter by lazy{
        CategoryAdminAdapter(requireContext(), onItemClick)
    }
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCategoryAdminBinding {
        return FragmentCategoryAdminBinding.inflate(inflater, container, false)
    }

    private fun setupRecycleViewLayout() {
        binding.rvCate.adapter = categoryAdapter
        binding.rvCate.layoutManager = GridLayoutManager(context, 2)
    }

    private fun observerEvent() {
        registerAllExceptionEvent(adminViewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(adminViewModel, viewLifecycleOwner)
        registerObserverNavigateEvent(adminViewModel, viewLifecycleOwner)
    }

    private fun setUpNavigate(){
        binding.imageButton.setOnClickListener {
            val action: NavDirections = FragmentAdminCategoryDirections.actionAdminCategorytFragmentToAdminManagerFragment()
            navigateAction(action)
        }
        binding.btnCreate.setOnClickListener {
            val action: NavDirections = FragmentAdminCategoryDirections.actionAdminCategorytFragmentToAdminEditCategorytFragment()
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
        adminViewModel.getALlCategories()
        val controller = findNavController()
    }

    private val onItemClick: (CategoryModel) -> Unit = {
        val action: NavDirections = FragmentAdminCategoryDirections.actionAdminCategorytFragmentToAdminEditCategorytFragment(it)
        navigateAction(action)
    }
}