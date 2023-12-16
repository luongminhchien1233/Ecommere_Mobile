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
import com.app.mobile_ecommerece.databinding.FragmentOrderAdminBinding
import com.app.mobile_ecommerece.model.CategoryModel
import com.app.mobile_ecommerece.model.OrderData
import com.app.mobile_ecommerece.ui.Admin.adapter.CategoryAdminAdapter
import com.app.mobile_ecommerece.ui.Admin.adapter.OrderAdminAdapter
import com.app.mobile_ecommerece.viewmodels.AdminViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class FragmentAdminOrder: BaseFragment<FragmentOrderAdminBinding>(true) {
    private val adminViewModel: AdminViewModel by activityViewModels()
    private val orderAdapter: OrderAdminAdapter by lazy{
        OrderAdminAdapter(requireContext(), onItemClick)
    }
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOrderAdminBinding {
        return FragmentOrderAdminBinding.inflate(inflater, container, false)
    }

    private fun setupRecycleViewLayout() {
        binding.rvOrder.adapter = orderAdapter
        binding.rvOrder.layoutManager = GridLayoutManager(context, 1)

    }

    private fun observerEvent() {
        registerAllExceptionEvent(adminViewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(adminViewModel, viewLifecycleOwner)
        registerObserverNavigateEvent(adminViewModel, viewLifecycleOwner)
    }

    private fun setUpNavigate(){
        binding.imageButton.setOnClickListener {
            val action: NavDirections = FragmentAdminOrderDirections.actionAdminOrderFragmentToAdminManagerFragment()
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
        adminViewModel.getALlOrder()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterList(newText.lowercase(Locale.ROOT))
                return false
            }
        })
        val controller = findNavController()
    }

    private val onItemClick: (OrderData) -> Unit = {
        val action: NavDirections = FragmentAdminOrderDirections.actionAdminOrderFragmentToOrderDetailAdminFragment(it)
        navigateAction(action)
    }

    private fun filterList(newText: String) {
        val neworderList: ArrayList<OrderData> = ArrayList()
        adminViewModel.ordersData.value!!.forEach { item ->
            if (item.phoneNumber.lowercase(Locale.ROOT).contains(newText)) {
                neworderList.add(item)
            }
        }
        if (neworderList.isEmpty()) {

        } else {
            orderAdapter.setFilterList(neworderList)
        }
    }
}