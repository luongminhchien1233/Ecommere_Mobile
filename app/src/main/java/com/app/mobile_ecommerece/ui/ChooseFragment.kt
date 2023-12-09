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
import com.app.mobile_ecommerece.databinding.FragmentChooseRoomBinding
import com.app.mobile_ecommerece.model.AddressModel
import com.app.mobile_ecommerece.model.RoomModel
import com.app.mobile_ecommerece.ui.adapter.AddressAdapter
import com.app.mobile_ecommerece.ui.adapter.RoomAdapter
import com.app.mobile_ecommerece.viewmodels.AddressViewModel
import com.app.mobile_ecommerece.viewmodels.StoreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseFragment : BaseFragment<FragmentChooseRoomBinding>(true) {
    private val storeViewModel: StoreViewModel by activityViewModels()
    private val roomAdapter: RoomAdapter by lazy{
        RoomAdapter(requireContext(), onRoomItemClick)
    }
    private var roomId: String = ""
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChooseRoomBinding {
        return FragmentChooseRoomBinding.inflate(inflater, container, false)
    }

    private fun setupRecycleViewLayout() {
        binding.rvRoom.adapter = roomAdapter
        binding.rvRoom.layoutManager =GridLayoutManager(context, 2)
    }

    private fun observerEvent() {
        registerAllExceptionEvent(storeViewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(storeViewModel, viewLifecycleOwner)
        registerObserverNavigateEvent(storeViewModel, viewLifecycleOwner)
    }

    private fun setUpNavigate(){
        binding.ibBack.setOnClickListener {
            val action: NavDirections = ChooseFragmentDirections.actionChooseRoomFragmentToStoreFragment(roomId)
            navigateAction(action)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.storeViewModel = storeViewModel
        observerEvent()
        setUpNavigate()
        setupRecycleViewLayout()
        storeViewModel.getALlRoom()
        val controller = findNavController()
    }

    private val onRoomItemClick: (RoomModel) -> Unit = {
        roomId = it._id
    }

}