package com.app.mobile_ecommerece.ui.Admin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.app.mobile_ecommerece.base.BaseFragment
import com.app.mobile_ecommerece.databinding.FragmentAdminStatisticBinding

import com.app.mobile_ecommerece.viewmodels.AdminViewModel

class FragmentAdminStatistic : BaseFragment<FragmentAdminStatisticBinding>(true) {
    private val adminViewModel: AdminViewModel by activityViewModels()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAdminStatisticBinding {
        return FragmentAdminStatisticBinding.inflate(inflater, container, false)
    }

    private fun setupRecycleViewLayout() {

    }

    private fun observerEvent() {
        registerAllExceptionEvent(adminViewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(adminViewModel, viewLifecycleOwner)
        registerObserverNavigateEvent(adminViewModel, viewLifecycleOwner)
    }

    private fun setUpNavigate(){
        binding.imageButton.setOnClickListener {
            val action: NavDirections = FragmentAdminStatisticDirections.actionAdminStatisticFragmentToAdminManagerFragment()
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
        adminViewModel.adminGetStatistic()
        adminViewModel.statisticData.observe(viewLifecycleOwner) { newItem ->
            binding.tv1.text = newItem.productCount.toString()
            binding.tv2.text = newItem.order.orderCount.toString()
            binding.tv3.text = newItem.userCount.toString()
            val formattedNumber = String.format("%,d", newItem.order.orderPriceSumMonth)
            binding.tv4.text = formattedNumber + "₫"
        }
        val controller = findNavController()
    }

}