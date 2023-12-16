package com.app.mobile_ecommerece.ui.Admin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.app.mobile_ecommerece.base.BaseFragment
import com.app.mobile_ecommerece.databinding.FragmentMyOrderDetailsBinding
import com.app.mobile_ecommerece.databinding.FragmentOrderDetailAdminBinding
import com.app.mobile_ecommerece.model.CartModel
import com.app.mobile_ecommerece.model.Request.OrderRequest
import com.app.mobile_ecommerece.model.ThirdParties.ProvinceModel
import com.app.mobile_ecommerece.ui.OrderDetailFragmentArgs
import com.app.mobile_ecommerece.ui.adapter.ProductOrderAdapter
import com.app.mobile_ecommerece.viewmodels.AdminViewModel
import com.app.mobile_ecommerece.viewmodels.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentOrderDetailAdmin : BaseFragment<FragmentOrderDetailAdminBinding>(true) {
    private val adminViewModel: AdminViewModel by activityViewModels()
    private val args by navArgs<OrderDetailFragmentArgs>()
    private val productOrderAdapter: ProductOrderAdapter by lazy{
        ProductOrderAdapter(requireContext(), onItemClick)
    }
    var orderStatus = ""
    var orderId = ""
    private lateinit var statusAdapter: ArrayAdapter<String>
    private var statusData: List<String> = listOf("Processing", "Dispatched", "Cancelled", "Delivered")
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOrderDetailAdminBinding {
        return FragmentOrderDetailAdminBinding.inflate(inflater, container, false)
    }

    private fun setupRecycleViewLayout() {
        binding.rvMyOrderListProduct.adapter = productOrderAdapter
        binding.rvMyOrderListProduct.layoutManager = GridLayoutManager(context, 1)
//        statusAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, statusData)
//        binding.autoCompleteTxtOrder.setAdapter(statusAdapter)
    }

    private fun observerEvent() {
        registerAllExceptionEvent(adminViewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(adminViewModel, viewLifecycleOwner)
        registerObserverNavigateEvent(adminViewModel, viewLifecycleOwner)
    }

    private fun setUpNavigate(){
        binding.btnBack.setOnClickListener {
            val action: NavDirections = FragmentOrderDetailAdminDirections.actionOrderDetailAdminFragmentToAdminOrderFragment()
            navigateAction(action)
        }
//        binding.autoCompleteTxtOrder.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
//            val itemSelect = parent.getItemAtPosition(position)
//            orderStatus = itemSelect.toString()
//        }
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
            orderStatus  = args.orderItem?.status.toString()
            if(orderStatus != "Cancelled"){
                statusAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, statusData)
                binding.autoCompleteTxtOrder.setAdapter(statusAdapter)
                binding.autoCompleteTxtOrder.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                    val itemSelect = parent.getItemAtPosition(position)
                    orderStatus = itemSelect.toString()
                }
                binding.btnUpdate.setOnClickListener {
                    Update()
                }
            }else{

            }
            orderId = args.orderItem?._id.toString()
            binding.tvTotalOrder.text = args.orderItem?.total.toString()
            binding.tvshippingAddress.text = args.orderItem?.addressShipping?.ward + ", " + args.orderItem?.addressShipping?.district + ", " + args.orderItem?.addressShipping?.province
        }

    }

    private val onItemClick: (CartModel) -> Unit = {

    }

    fun Update(){
        val orderRequest = OrderRequest(orderStatus)
        adminViewModel.updateOrder(orderRequest, orderId)
    }
}