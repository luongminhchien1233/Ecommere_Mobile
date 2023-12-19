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
import com.app.mobile_ecommerece.databinding.FragmentOrderAdminBinding
import com.app.mobile_ecommerece.databinding.FragmentProductAdminBinding
import com.app.mobile_ecommerece.model.OrderData
import com.app.mobile_ecommerece.model.ProductAdminModel
import com.app.mobile_ecommerece.ui.Admin.adapter.OrderAdminAdapter
import com.app.mobile_ecommerece.ui.Admin.adapter.ProductAdminAdapter
import com.app.mobile_ecommerece.viewmodels.AdminViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class FragmentAdminProduct: BaseFragment<FragmentProductAdminBinding>(true) {
    private val adminViewModel: AdminViewModel by activityViewModels()
    private val productAdapter: ProductAdminAdapter by lazy{
        ProductAdminAdapter(requireContext(), onItemClick)
    }
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProductAdminBinding {
        return FragmentProductAdminBinding.inflate(inflater, container, false)
    }

    private fun setupRecycleViewLayout() {
        binding.rvProduct.adapter = productAdapter
        binding.rvProduct.layoutManager = GridLayoutManager(context, 1)

    }

    private fun observerEvent() {
        registerAllExceptionEvent(adminViewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(adminViewModel, viewLifecycleOwner)
        registerObserverNavigateEvent(adminViewModel, viewLifecycleOwner)
    }

    private fun setUpNavigate(){
        binding.imageButton.setOnClickListener {
            val action: NavDirections = FragmentAdminProductDirections.actionAdminProductFragmentToAdminManagerFragment()
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
        adminViewModel.getAllProducts()
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

    private val onItemClick: (ProductAdminModel) -> Unit = {

    }

    private fun filterList(newText: String) {
        val newList: ArrayList<ProductAdminModel> = ArrayList()
        adminViewModel.productsData.value!!.forEach { item ->
            if (item.name.lowercase(Locale.ROOT).contains(newText)) {
                newList.add(item)
            }
        }
        if (newList.isEmpty()) {

        } else {
            productAdapter.setFilterList(newList)
        }
    }
}