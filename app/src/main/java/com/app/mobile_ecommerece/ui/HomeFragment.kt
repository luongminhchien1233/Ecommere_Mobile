package com.app.mobile_ecommerece.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.mobile_ecommerece.base.BaseFragment
import com.app.mobile_ecommerece.databinding.FragmentHomeBinding
import com.app.mobile_ecommerece.model.CategoryModel
import com.app.mobile_ecommerece.model.ProductModel
import com.app.mobile_ecommerece.ui.adapter.CategoryAdapter
import com.app.mobile_ecommerece.ui.adapter.ProductAdapter
import com.app.mobile_ecommerece.viewmodels.HomeViewModel
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(false) {


    private val homeViewModel: HomeViewModel by activityViewModels()

    private val categoryAdapter: CategoryAdapter by lazy{
        CategoryAdapter(requireContext(), onCategoryIconClick)
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    private fun observerEvent() {
        registerAllExceptionEvent(homeViewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(homeViewModel, viewLifecycleOwner)
        registerObserverNavigateEvent(homeViewModel, viewLifecycleOwner)
    }

    private fun setupRecycleViewLayout() {
        binding.rvCategory.adapter =
            categoryAdapter
        binding.rvCategory.layoutManager = GridLayoutManager(requireContext(), 4)

        binding.rvNewArrivalsHome.adapter = ProductAdapter(requireContext(), onProductItemClick)
        binding.rvNewArrivalsHome.layoutManager = GridLayoutManager(context, 1)
//
//        binding.layoutCategoryList.adapter =
//            CategoryButtonAdapter(requireContext(), onCategoryItemButtonClick)
//        binding.layoutCategoryList.layoutManager =
//
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.homeViewModel = homeViewModel
        observerEvent()
        setupRecycleViewLayout()

        homeViewModel.fetchData()

//        homeViewModel.categoriesData.observe(viewLifecycleOwner , Observer {
//            categoryAdapter.setItems(it)
//        })

//        if (homeViewModel.userLiveData.value == null) {
//            homeViewModel.fetchData()
//        }

        val controller = findNavController()
    }

//    private val onCategoryItemButtonClick: (CategoryRadioButton) -> Unit = {
//        homeViewModel.fetchProductsByCategoryId(it.id)
//        homeViewModel.isProductsLoading.observe(viewLifecycleOwner, EventObserver { isShow ->
//            if (isShow)
//                binding.productsLoadingLayout.visibility = View.VISIBLE
//            else
//                binding.productsLoadingLayout.visibility = View.GONE
//        })
//    }
//
//    private val onCategoryIconButtonClick: OnCategoryIconButtonClick = {
////        binding.layoutProductList.loadProductByCategoryId(0)
//    }


    private val onProductItemClick: (ProductModel) -> Unit = {

    }

    private val onCategoryIconClick: (CategoryModel) -> Unit = {

    }
}