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
import com.app.mobile_ecommerece.databinding.FragmentAddressListBinding
import com.app.mobile_ecommerece.databinding.FragmentPlaceOrderBinding
import com.app.mobile_ecommerece.model.AddressModel
import com.app.mobile_ecommerece.model.CartModel
import com.app.mobile_ecommerece.model.OrderProductModel
import com.app.mobile_ecommerece.ui.adapter.AddressAdapter
import com.app.mobile_ecommerece.ui.adapter.ProductOrderAdapter
import com.app.mobile_ecommerece.viewmodels.AddressViewModel
import com.app.mobile_ecommerece.viewmodels.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckOutFragment : BaseFragment<FragmentPlaceOrderBinding>(true) {
    private val orderViewModel: OrderViewModel by activityViewModels()
    private val args by navArgs<CheckOutFragmentArgs>()
    private val productOrderAdapter: ProductOrderAdapter by lazy{
        ProductOrderAdapter(requireContext(), onItemClick)
    }
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPlaceOrderBinding {
        return FragmentPlaceOrderBinding.inflate(inflater, container, false)
    }

    private fun setupRecycleViewLayout() {
        binding.rvProductOrder.adapter = productOrderAdapter
        binding.rvProductOrder.layoutManager = GridLayoutManager(context, 1)
    }

    private fun observerEvent() {
        registerAllExceptionEvent(orderViewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(orderViewModel, viewLifecycleOwner)
        registerObserverNavigateEvent(orderViewModel, viewLifecycleOwner)
    }

    private fun setUpNavigate(){
        binding.imageButton.setOnClickListener {
            val action: NavDirections = CheckOutFragmentDirections.actionCheckoutFragmentToCartFragment()
            navigateAction(action)
        }
        binding.btnAddress.setOnClickListener {
            val action: NavDirections = CheckOutFragmentDirections.actionCheckoutFragmentToChangeAddressFragment()
            navigateAction(action)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.orderViewModel = orderViewModel
        observerEvent()
        setUpNavigate()
        setupRecycleViewLayout()

        if(args.addressSelected == null){
            orderViewModel.getAllAddress()
            orderViewModel.getProductInCart()
            orderViewModel.getProfile()
        }
        else{
            orderViewModel.upLoadSelectAddress(args.addressSelected!!)
            orderViewModel.getProductInCart()
            orderViewModel.getProfile()
        }
        orderViewModel.totalPriceData.observe(viewLifecycleOwner) { value ->
            binding.tvTotalPricePlaceOrder.text = value.toString()
        }
        orderViewModel.userInfoLiveData.observe(viewLifecycleOwner) { value ->
            binding.ipName.text = value.firstname + " " + value.lastname
            binding.ipEmail.text =  value.email
            binding.ipPhoneNumber.text = value.phoneNumber
        }
        orderViewModel.addressChooseData.observe(viewLifecycleOwner) { value ->
            binding.tvProvince.text = value.province
            binding.tvDisctrict.text = value.district
            binding.tvWard.text = value.ward
            binding.tvNote.text = value.note
        }
        val controller = findNavController()
    }
    private val onItemClick: (CartModel) -> Unit = {

    }

}