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
import com.app.mobile_ecommerece.model.AddressModel
import com.app.mobile_ecommerece.model.CartModel
import com.app.mobile_ecommerece.model.Request.CartRequest
import com.app.mobile_ecommerece.ui.adapter.AddressAdapter
import com.app.mobile_ecommerece.ui.adapter.CartAdapter
import com.app.mobile_ecommerece.viewmodels.AddressViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddressListFragment : BaseFragment<FragmentAddressListBinding>(true) {
    private val addressViewModel: AddressViewModel by activityViewModels()
    private val addressAdapter: AddressAdapter by lazy{
        AddressAdapter(requireContext(), onItemClick)
    }
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAddressListBinding {
        return FragmentAddressListBinding.inflate(inflater, container, false)
    }

    private fun setupRecycleViewLayout() {
        binding.rvAddressItem.adapter = addressAdapter
        binding.rvAddressItem.layoutManager = GridLayoutManager(context, 1)
    }

    private fun observerEvent() {
        registerAllExceptionEvent(addressViewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(addressViewModel, viewLifecycleOwner)
        registerObserverNavigateEvent(addressViewModel, viewLifecycleOwner)
    }

    private fun setUpNavigate(){
        binding.ibBack.setOnClickListener {
            val action: NavDirections = AddressListFragmentDirections.actionAddressListFragmentToProfileFragment()
            navigateAction(action)
        }
        binding.btnAdd.setOnClickListener {
            val action: NavDirections = AddressListFragmentDirections.actionAddressListFragmentToAddAddressFragment()
            navigateAction(action)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.addressViewModel = addressViewModel
        observerEvent()
        setUpNavigate()
        setupRecycleViewLayout()
        addressViewModel.getAllAddress()
        val controller = findNavController()
    }

    private val onItemClick: (AddressModel) -> Unit = {

    }

}