package com.app.mobile_ecommerece.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.app.mobile_ecommerece.base.BaseFragment
import com.app.mobile_ecommerece.databinding.FragmentMyOrderBinding
import com.app.mobile_ecommerece.databinding.FragmentMyOrderDetailsBinding
import com.app.mobile_ecommerece.model.*
import com.app.mobile_ecommerece.ui.adapter.AddressAdapter
import com.app.mobile_ecommerece.ui.adapter.OrderAdapter
import com.app.mobile_ecommerece.ui.adapter.ProductOrderAdapter
import com.app.mobile_ecommerece.viewmodels.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderDetailFragment : BaseFragment<FragmentMyOrderDetailsBinding>(true) {
    private val orderViewModel: OrderViewModel by activityViewModels()
    private val args by navArgs<OrderDetailFragmentArgs>()
    private val productOrderAdapter: ProductOrderAdapter by lazy{
        ProductOrderAdapter(requireContext(), onItemClick)
    }
    var orderId = ""
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMyOrderDetailsBinding {
        return FragmentMyOrderDetailsBinding.inflate(inflater, container, false)
    }

    private fun setupRecycleViewLayout() {
        binding.rvMyOrderListProduct.adapter = productOrderAdapter
        binding.rvMyOrderListProduct.layoutManager = GridLayoutManager(context, 1)
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
        observerEvent()
        setUpNavigate()
        setupRecycleViewLayout()
        val controller = findNavController()
        if(args.orderItem != null){
            binding.orderData = args.orderItem
            orderId = args.orderItem?._id.toString()
            binding.tvTotalOrder.text = args.orderItem?.total.toString()
            binding.tvshippingAddress.text = args.orderItem?.addressShipping?.ward + ", " + args.orderItem?.addressShipping?.district + ", " + args.orderItem?.addressShipping?.province
            if(args.orderItem?.status != "Cancelled"){
                binding.btnCancel.visibility = View.VISIBLE
                binding.btnCancel.setOnClickListener {
                    orderViewModel.cancelOrder(orderId)
                }
            }
            else{
                binding.btnCancel.visibility = View.GONE
            }
        }

    }

    private val onItemClick: (CartModel) -> Unit = {

    }
}