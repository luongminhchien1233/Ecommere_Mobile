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
import com.app.mobile_ecommerece.R
import com.app.mobile_ecommerece.base.BaseFragment
import com.app.mobile_ecommerece.databinding.FragmentHomeBinding
import com.app.mobile_ecommerece.model.CategoryModel
import com.app.mobile_ecommerece.model.ProductModel
import com.app.mobile_ecommerece.model.RoomModel
import com.app.mobile_ecommerece.ui.adapter.CategoryAdapter
import com.app.mobile_ecommerece.ui.adapter.ProductAdapter
import com.app.mobile_ecommerece.ui.adapter.RoomAdapter
import com.app.mobile_ecommerece.viewmodels.HomeViewModel
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(false) {


    private val homeViewModel: HomeViewModel by activityViewModels()

    private val roomAdapter: RoomAdapter by lazy{
        RoomAdapter(requireContext(), onRoomIconClick)
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
        binding.rvRoom.adapter =
            roomAdapter
        binding.rvRoom.layoutManager = GridLayoutManager(requireContext(), 4)

        binding.rvNewArrivalsHome.adapter = ProductAdapter(requireContext(), onProductItemClick)
        binding.rvNewArrivalsHome.layoutManager = GridLayoutManager(context, 2)
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

        binding.btnLogOut.setOnClickListener {
            homeViewModel.logout()
            navigateToPage(R.id.action_homeFragment_to_loginFragment);
        }

        binding.tvViewAllNew.setOnClickListener {
            val action: NavDirections = HomeFragmentDirections.actionHomeFragmentToStoreFragment("")
            navigateAction(action)
        }

        binding.tvNewArrivals.setOnClickListener {
            val action: NavDirections = HomeFragmentDirections.actionHomeFragmentToStoreFragment("")
            navigateAction(action)
        }

        val controller = findNavController()
    }

    private val onProductItemClick: (ProductModel) -> Unit = {
        val action: NavDirections = HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(it)
        navigateAction(action)
    }

    private val onRoomIconClick: (RoomModel) -> Unit = {
        val action: NavDirections = HomeFragmentDirections.actionHomeFragmentToStoreFragment(it._id)
        navigateAction(action)
    }
}