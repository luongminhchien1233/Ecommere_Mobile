package com.app.mobile_ecommerece.ui

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
import com.app.mobile_ecommerece.databinding.FragmentMyOrderBinding
import com.app.mobile_ecommerece.model.AddressModel
import com.app.mobile_ecommerece.model.OrderData
import com.app.mobile_ecommerece.model.OrderUserData
import com.app.mobile_ecommerece.ui.adapter.AddressAdapter
import com.app.mobile_ecommerece.ui.adapter.OrderAdapter
import com.app.mobile_ecommerece.viewmodels.AddressViewModel
import com.app.mobile_ecommerece.viewmodels.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderListFragment : BaseFragment<FragmentMyOrderBinding>(true) {
    private val orderViewModel: OrderViewModel by activityViewModels()
    private val orderAdapter: OrderAdapter by lazy{
        OrderAdapter(requireContext(), onItemClick)
    }
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMyOrderBinding {
        return FragmentMyOrderBinding.inflate(inflater, container, false)
    }

    private fun setupRecycleViewLayout() {
        binding.rvMyOrderList.adapter = orderAdapter
        binding.rvMyOrderList.layoutManager = GridLayoutManager(context, 1)
    }

    private fun observerEvent() {
        registerAllExceptionEvent(orderViewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(orderViewModel, viewLifecycleOwner)
        registerObserverNavigateEvent(orderViewModel, viewLifecycleOwner)
    }

    private fun setUpNavigate(){
        binding.btnBack.setOnClickListener {
            navigateBack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.orderViewModel = orderViewModel
        observerEvent()
        setUpNavigate()
        setupRecycleViewLayout()
        orderViewModel.getUserOrder()
        val controller = findNavController()
    }

    private val onItemClick: (OrderUserData) -> Unit = {
        val action: NavDirections = OrderListFragmentDirections.actionOrderListFragmentToOrderDetailFragment(it)
        navigateAction(action)
    }

}