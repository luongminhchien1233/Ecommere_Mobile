package com.app.mobile_ecommerece.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.app.mobile_ecommerece.base.BaseFragment
import com.app.mobile_ecommerece.databinding.FragmentCartBinding
import com.app.mobile_ecommerece.model.CartModel
import com.app.mobile_ecommerece.model.Request.CartRequest
import com.app.mobile_ecommerece.ui.adapter.CartAdapter
import com.app.mobile_ecommerece.viewmodels.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding>(false) {
    private val cartViewModel: CartViewModel by activityViewModels()
    private val cartAdapter: CartAdapter by lazy{
        CartAdapter(requireContext(), onPlusClick, onMinusClick, onDeleteClick)
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCartBinding {
        return FragmentCartBinding.inflate(inflater, container, false)
    }

    private fun observerEvent() {
        registerAllExceptionEvent(cartViewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(cartViewModel, viewLifecycleOwner)
        registerObserverNavigateEvent(cartViewModel, viewLifecycleOwner)
    }

    private fun setupRecycleViewLayout() {
        binding.rvListCart.adapter = cartAdapter
        binding.rvListCart.layoutManager = GridLayoutManager(context, 1)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.cartViewModel = cartViewModel
        observerEvent()
        setupRecycleViewLayout()
        if(cartViewModel.checkIsLogin()){
            cartViewModel.setEmpty()
            cartViewModel.getCart()
        }
        binding.tvClearAll.setOnClickListener {
            cartViewModel.clearCart()
            cartAdapter.clear()
            binding.tvTotalCart.text = "0";
        }
        //binding.tvTotalCart.text = binding.cartViewModel.cartData.value!!.cartTotal.toString()
        val controller = findNavController()
    }

    private val onMinusClick: (CartModel) -> Unit = {
        var itemQty: Int = it.quantity;
        val cartUpdateRequest = CartRequest(it.product._id, itemQty - 1);
        cartViewModel.updateCart(cartUpdateRequest)
        if (cartViewModel.cartItemData.value?.size == 1 && ((itemQty - 1) == 0)) {
            cartAdapter.clear()
            binding.tvTotalCart.text = "0";
        }
    }

    private val onPlusClick: (CartModel) -> Unit = {
        var itemQty: Int = it.quantity;
        val cartUpdateRequest = CartRequest(it.product._id, itemQty + 1);
        cartViewModel.updateCart(cartUpdateRequest)
    }

    private val onDeleteClick: (CartModel) -> Unit = {
        val cartUpdateRequest = CartRequest(it.product._id, 0);
        cartViewModel.updateCart(cartUpdateRequest)
        if (cartViewModel.cartItemData.value?.size == 1) {
            cartAdapter.clear()
            binding.tvTotalCart.text = "0";
        }
    }

}